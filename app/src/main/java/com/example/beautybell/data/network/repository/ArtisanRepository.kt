package com.example.beautybell.data.network.repository

import android.content.Context
import com.example.beautybell.data.model.Artisan
import com.example.beautybell.data.model.ResponseArtisanList
import com.example.beautybell.data.network.ApiObserver
import com.example.beautybell.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ArtisanRepository(context: Context) {

    private val compositeDisposable = CompositeDisposable()
    private val apiService = ApiService.buildRetrofit(context)

    fun fetchListArtisan(
        onResult: (ResponseArtisanList) -> Unit,
        onError: (Throwable) -> Unit) {

        apiService.fetchListArtisan()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<ResponseArtisanList>(compositeDisposable) {
                override fun onApiSuccess(data: ResponseArtisanList) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun fetchArtisan(
        onResult: (Artisan) -> Unit,
        onError: (Throwable) -> Unit,
        id: String) {

        apiService.fetchArtisan(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<Artisan>(compositeDisposable) {
                override fun onApiSuccess(data: Artisan) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }
}