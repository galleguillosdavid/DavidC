package com.example.david.repository

import android.util.Log
import com.example.david.repository.networkWithRetrofit.ProductsNetwork
import com.example.david.repository.networkWithRetrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository {
    private val retroService= RetrofitClient.getRetrofitClient()

    //Vieja confiable
    fun getDataFromServer() {
        val call= retroService.fechAllProducts()
        call.enqueue(object :Callback<ProductsNetwork>{
            override fun onResponse(
                call: Call<ProductsNetwork>,
                response: Response<ProductsNetwork>
            ) {
                when(response.code()){
                    in 200..299 -> Log.d("Response",response.body().toString())
                    in 300..399 -> Log.d("Response",response.body().toString())
                    in 400..499 -> Log.d("Response",response.body().toString())
                    in 500..599 -> Log.d("Response",response.body().toString())
                    else -> Log.d("Error",response.body().toString())
                }

            }

            override fun onFailure(call: Call<ProductsNetwork>, t: Throwable) {
                Log.e("Error",t.message.toString())
            }

        })
    }
}