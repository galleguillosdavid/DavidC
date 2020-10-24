package com.example.david.networkWithRetrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products/")
    fun fechAllProducts(): Call<ProductsNetwork>

    @GET("products/")
    suspend fun fetchAllProductsCorroutines(): Response<ProductsNetwork>
}