package com.example.david.repository.localWithRoom.networkWithRetrofit

import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products/")
    suspend fun fetchAllProductsCorroutines(): Response<List<Products>>
}