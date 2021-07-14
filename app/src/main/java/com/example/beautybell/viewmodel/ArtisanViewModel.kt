package com.example.beautybell.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.beautybell.data.model.Artisan
import com.example.beautybell.data.model.ResponseArtisanList
import com.example.beautybell.data.network.repository.ArtisanRepository

class ArtisanViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ArtisanRepository(application.applicationContext.applicationContext)
    private val context = application.applicationContext.applicationContext

    var artisalListLiveData: MutableLiveData<MutableList<Artisan>> = MutableLiveData()
    var artisalLiveData: MutableLiveData<Artisan> = MutableLiveData()

    var errorArtisanListLiveData: MutableLiveData<Throwable> = MutableLiveData()
    var errorArtisanLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun fetchArtisanList() {
        repository.fetchListArtisan({
            artisalListLiveData.postValue(it)
        }, {
            errorArtisanListLiveData.postValue(Throwable("Terjadi kesalahan"))
        })
    }

    fun fetchArtisan(
        id: String) {
        repository.fetchArtisan({
            artisalLiveData.postValue(it)
        }, {
            errorArtisanLiveData.postValue(Throwable("Terjadi kesalahan"))
        }, id)
    }
}