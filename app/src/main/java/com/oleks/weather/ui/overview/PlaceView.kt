package com.oleks.weather.ui.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Locale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oleks.weather.data.geonames.PlacesRepo
import com.oleks.weather.data.geonames.model.Geoname
import kotlinx.coroutines.delay

class PlaceView constructor(private val repo: PlacesRepo) : ViewModel(
) {

    private var errorMessage by mutableStateOf("")
    private val debouncePeriod = 1000L

    private var job: Job? = null

    val language = Locale.getDefault().language

    var places by mutableStateOf(emptyList<Geoname>())
    var loading = MutableLiveData(false)

    var place: Geoname? = null

    var choosen = false

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun getPlaces(name: String){
        job = viewModelScope.launch {
            delay(debouncePeriod)
            try {
                val list = repo.searchPlaces(name, language)
                places = list
                loading.value = false
                Log.i("TEST", places[0].name)
            } catch (e: Exception) {
                onError("Exception handled: ${e.localizedMessage}")
            }
        }
    }

    fun debouncedSearch(text: String) {
        job?.cancel()
        getPlaces(text)
    }

    private fun onError(message: String){
        errorMessage = message
        Log.e("ViewModel", errorMessage)
        loading.value = false
        job?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}