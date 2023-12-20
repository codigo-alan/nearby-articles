package com.example.nearbyarticles.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.nearbyarticles.R
import com.example.nearbyarticles.databinding.FragmentListBinding
import com.example.nearbyarticles.domain.model.Item
import com.example.nearbyarticles.ui.adapters.ItemAdapter
import com.example.nearbyarticles.ui.adapters.OnClickListener
import com.example.nearbyarticles.ui.viewmodel.ListViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng


class ListFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val model: ListViewModel by activityViewModels()

    //to obtain self location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            fetchRemoteData(!(isGranted.containsValue(false)))
        }//Request and check permissions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        if (model.currentSearch.value == null) getLocation()


        return binding.root
    }

    @SuppressLint("SetTextI18n")
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
            //calculate distances
            model.calculateDistance()
            itemAdapter.setItems(it)
        }
        model.successfulQuery.observe(viewLifecycleOwner){

            if (it == null) binding.tvLoading.visibility = View.VISIBLE
            else{
                binding.tvLoading.visibility = View.GONE
                if (it && model.items.value!!.isEmpty()) binding.tvNoData.visibility = View.VISIBLE
                if (it && model.items.value!!.isNotEmpty()) binding.tvNoData.visibility = View.GONE
                if (!it) binding.tvErrorData.visibility = View.VISIBLE
            }

        }//Handle views to show (Error, No data or List)
        model.currentCoordinates.observe(viewLifecycleOwner) {
            binding.etCoordinates.setText(
                "${model.currentCoordinates.value?.latitude}," +
                        "${model.currentCoordinates.value?.longitude}")
        }
        model.currentSearch.observe(viewLifecycleOwner){
            binding.etCoordinates.setText(
                "${model.currentSearch.value?.latitude}," +
                        "${model.currentSearch.value?.longitude}")
        }

        binding.fabOwnSearch.setOnClickListener {
            getLocation()
            //Log.d("devCoordinatesFab", "${model.currentCoordinates.value}")
        }
        binding.fabNewSearch.setOnClickListener {
            try {
                val coordinatesToSearch = binding.etCoordinates.text.split(",")

                val latitude: Double = coordinatesToSearch.first().toDouble()
                val longitude: Double = coordinatesToSearch.last().toDouble()
                model.setCurrentCoordinates(LatLng(latitude, longitude))
                model.setCurrentSearch(LatLng(latitude, longitude))
                model.remoteFetchData(LatLng(latitude, longitude))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Complete properly the coordinates field\n" +
                        "'latitude,longitude'", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getLocation() {
        val permissions = listOf<String>(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        requestPermissionLauncher.launch(permissions.toTypedArray()) //launch the request created in the top
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