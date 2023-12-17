package com.example.nearbyarticles.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearbyarticles.data.remote.RemoteDataSource
import com.example.nearbyarticles.data.repository.Repository
import com.example.nearbyarticles.domain.model.Item
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel : ViewModel() {

    //TODO inject repo with Dagger Hilt
    val repository = Repository()

    private val _items = MutableLiveData<List<Item>>().apply { value = listOf() }
    val items: LiveData<List<Item>> = _items

    private val _selectedItem = MutableLiveData<Item>()
    val selectedItem: LiveData<Item> = _selectedItem

    private val _currentCoordinates = MutableLiveData<LatLng>()
    val currentCoordinates: LiveData<LatLng> = _currentCoordinates

    init {
        remoteFetchData()
    }

    private fun remoteFetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.remoteDataSource.fetchData() //fetch data from repo
                _items.postValue(repository.remoteDataSource.itemsRemote.value)
            }
        }
    }

    fun setSelectedItem(clickedItem: Item){
        _selectedItem.postValue(clickedItem)
    }

    fun setCurrentCoordinates(newCoordinates: LatLng){
        _currentCoordinates.postValue(newCoordinates)
    }

}