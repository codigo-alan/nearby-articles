package com.example.nearbyarticles.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearbyarticles.data.repository.Repository
import com.example.nearbyarticles.domain.model.Item
import com.example.nearbyarticles.utils.haversine
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    //TODO inject repo with Dagger Hilt
    //val repository = Repository()

    private val _items = repository.itemsRepo
    val items: LiveData<List<Item>> = _items

    private val _selectedItem = MutableLiveData<Item>()
    val selectedItem: LiveData<Item> = _selectedItem

    private val _currentCoordinates = MutableLiveData<LatLng>()
    val currentCoordinates: LiveData<LatLng> = _currentCoordinates

    private val _currentSearch = MutableLiveData<LatLng>()
    val currentSearch: LiveData<LatLng> = _currentSearch

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
    }

    fun setSelectedItem(clickedItem: Item){
        _selectedItem.postValue(clickedItem)
    }

    fun setCurrentCoordinates(newCoordinates: LatLng){
        _currentCoordinates.postValue(newCoordinates)
    }

    fun setCurrentSearch(newCoordinates: LatLng) {
        _currentSearch.postValue(newCoordinates)
    }

    fun calculateDistance() {
        _items.value?.forEach { item ->
            item.distance = _currentCoordinates.value?.let {
                haversine(
                    it,
                    LatLng(item.coordinates.first().lat, item.coordinates.first().lon))
            }
         }
    }

}