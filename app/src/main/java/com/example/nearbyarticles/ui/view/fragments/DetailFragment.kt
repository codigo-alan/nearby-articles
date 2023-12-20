package com.example.nearbyarticles.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nearbyarticles.R
import com.example.nearbyarticles.databinding.FragmentDetailBinding
import com.example.nearbyarticles.domain.model.Item
import com.example.nearbyarticles.ui.viewmodel.ListViewModel
import com.example.nearbyarticles.utils.limitLength


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val model: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: Item = model.selectedItem.value!!

        binding.tvSelectedName.text = item.title

        val stringImage = item.thumbnail?.source

        if (stringImage != null) {
            context?.let {
                Glide.with(it)
                    .load(item.thumbnail.source)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //save in cache to avoid unneeded resources consume
                    .centerCrop()
                    .error(R.drawable.baseline_broken_image_24)
                    .into(binding.ivSelectedImage)
            } //put the image in te image view
        }

        binding.tvLatitude.text = "latitude: ${item.coordinates.first().lat}"
        binding.tvLongitude.text = "longitude: ${item.coordinates.first().lon}"

        binding.tvSelectedDistance.text =
            "${item.distance.toString().limitLength(8, true)} Km"

        binding.toListButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }


    }

}