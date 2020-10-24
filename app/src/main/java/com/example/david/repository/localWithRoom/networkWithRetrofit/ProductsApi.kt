package com.example.david.repository.localWithRoom.networkWithRetrofit

import com.example.david.repository.localWithRoom.networkWithRetrofit.pojo.ProductsNetworkItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products/")
    fun fechAllProducts(): Call<List<ProductsNetworkItem>>

    @GET("products/")
    suspend fun fetchAllProductsCorroutines(): Response<List<ProductsNetworkItem>>
}