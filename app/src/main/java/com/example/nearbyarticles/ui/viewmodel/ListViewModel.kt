package com.example.nearbyarticles.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearbyarticles.data.repository.Repository
import com.example.nearbyarticles.domain.model.Item
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel : ViewModel() {

    //TODO inject repo with Dagger Hilt
    val repository = Repository()

    //private val _items = MutableLiveData<List<Item>>().apply { value = listOf() }
    private val _items = repository.itemsRepo
    val items: LiveData<List<Item>> = _items

    private val _selectedItem = MutableLiveData<Item>()
    val selectedItem: LiveData<Item> = _selectedItem

    private val _currentCoordinates = MutableLiveData<LatLng>()
    val currentCoordinates: LiveData<LatLng> = _currentCoordinates

    private var _successfulQuery = repository.successfulQuery
    val successfulQuery: LiveData<Boolean?> = _successfulQuery

    fun remoteFetchData(coordinates: LatLng) {
        /*viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.remoteFetchData(coordinates) //fetch data from repo
                _items.postValue(repository.itemsRepo.value)
                Log.d("devApiVMItems", "${items.value}")
            }
        }*/
        repository.remoteFetchData(coordinates) //fetch data from repo
        Log.d("devApiVMItems", "${items.value}")
    }

    fun setSelectedItem(clickedItem: Item){
        _selectedItem.postValue(clickedItem)
    }

    fun setCurrentCoordinates(newCoordinates: LatLng){
        _currentCoordinates.postValue(newCoordinates)
    }

}