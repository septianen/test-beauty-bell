package com.example.beautybell.data.model

import com.google.gson.annotations.SerializedName

class Artisan {
    @SerializedName("id")
    val id: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("avatar")
    var avatar: String? = null

    @SerializedName("image")
    val image: String? = null

    @SerializedName("user_image")
    val userImage: String? = null

    @SerializedName("rating")
    val rating: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("services")
    val services: MutableList<Service>? = null
}