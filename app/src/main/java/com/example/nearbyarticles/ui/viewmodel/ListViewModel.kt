package com.example.nearbyarticles.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nearbyarticles.domain.model.Item
import com.google.android.gms.maps.model.LatLng

class ListViewModel : ViewModel() {

    //TODO inject repo with Dagger Hilt

    private val _items = MutableLiveData<List<Item>>().apply { value = listOf() }
    val items: LiveData<List<Item>> = _items

    private val _selectedItem = MutableLiveData<Item>()
    val selectedItem: LiveData<Item> = _selectedItem

    private val _currentCoordinates = MutableLiveData<LatLng>()
    val currentCoordinates: LiveData<LatLng> = _currentCoordinates

    init {
        _items.value = listOf(Item(0,"racing", "empty.jpg"),Item(1,"river", "empty.jpg")) //mocked
    }

    fun setSelectedItem(clickedItem: Item){
        _selectedItem.postValue(clickedItem)
    }

    fun setCurrentCoordinates(newCoordinates: LatLng){
        _currentCoordinates.postValue(newCoordinates)
    }

}