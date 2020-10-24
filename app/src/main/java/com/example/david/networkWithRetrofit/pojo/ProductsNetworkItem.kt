package com.example.david.networkWithRetrofit.pojo


import com.google.gson.annotations.SerializedName

data class ProductsNetworkItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)