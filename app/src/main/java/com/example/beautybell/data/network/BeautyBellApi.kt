package com.example.beautybell.data.network

import com.example.beautybell.data.model.Artisan
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BeautyBellApi {

    @GET("list-artisan")
    fun fetchListArtisan(): Observable<MutableList<Artisan>>

    @GET("list-artisan/{id}")
    fun fetchArtisan(
        @Path("id") id: String): Observable<Artisan>
}