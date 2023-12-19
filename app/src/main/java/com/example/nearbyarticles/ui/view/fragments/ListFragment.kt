package com.example.nearbyarticles.ui.view.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.nearbyarticles.R
import com.example.nearbyarticles.databinding.FragmentListBinding
import com.example.nearbyarticles.domain.model.Item
import com.example.nearbyarticles.ui.adapters.ItemAdapter
import com.example.nearbyarticles.ui.adapters.OnClickListener
import com.example.nearbyarticles.ui.viewmodel.ListViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng


const val REQUEST_CODE_LOCATION = 100

class ListFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val model: ListViewModel by activityViewModels()

    //to obtain self location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    //private lateinit var currentCoordinates: LatLng
    //Request and check permissions
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            fetchRemoteData(!(isGranted.containsValue(false)))
        }

    @SuppressLint("MissingPermission")
    private fun fetchRemoteData(isLocationGranted: Boolean) {
        if (isLocationGranted) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    //Log.d("devLocation", "$location")
                    model.setCurrentCoordinates(LatLng(location.latitude, location.longitude))
                    model.remoteFetchData(LatLng(location.latitude, location.longitude))
                }
            }
        }else Toast.makeText(
            requireContext(),
            "Are necessary location permissions to use the features of the app",
            Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        getLocation()

        return binding.root
    }

    private fun getLocation() {
        val permissions = listOf<String>(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        requestPermissionLauncher.launch(permissions.toTypedArray()) //launch the request created in the top
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter(model.items.value!!, this)

        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerListItems.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = itemAdapter
        }

        restartVisibilities()

        model.items.observe(viewLifecycleOwner) {
            itemAdapter.setItems(it)
            /*if (it.isEmpty()) binding.tvNoData.visibility = View.VISIBLE
            else binding.tvNoData.visibility = View.GONE*/
        }

        //Handle views to show (Error, No data or List)
        model.successfulQuery.observe(viewLifecycleOwner){

            if (it == null) binding.tvLoading.visibility = View.VISIBLE
            else{
                binding.tvLoading.visibility = View.GONE
                if (it && model.items.value!!.isEmpty()) binding.tvNoData.visibility = View.VISIBLE
                if (!it) binding.tvErrorData.visibility = View.VISIBLE
            }

        }


        model.currentCoordinates.observe(viewLifecycleOwner) {
            //Log.d("devCoordinates", "${model.currentCoordinates.value}")
            binding.tvCoordinates.text =
                "${model.currentCoordinates.value ?: "obtaining location..."}"
        }

        binding.fabNewSearch.setOnClickListener {
            getLocation()
            //Log.d("devCoordinatesFab", "${model.currentCoordinates.value}")
        }

    }

    private fun restartVisibilities() {
        binding.tvNoData.visibility = View.GONE
        binding.tvErrorData.visibility = View.GONE
        binding.tvLoading.visibility = View.GONE
    }

    override fun onClick(item: Item) {
        model.setSelectedItem(item)
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }
}