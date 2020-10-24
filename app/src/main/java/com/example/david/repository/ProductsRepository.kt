package com.example.david.repository

import android.util.Log
import com.example.david.repository.localWithRoom.ProductsDao
import com.example.david.repository.localWithRoom.ProductsEntity
import com.example.david.repository.localWithRoom.networkWithRetrofit.ProductsNetwork
import com.example.david.repository.localWithRoom.networkWithRetrofit.RetrofitClient
import com.example.david.repository.localWithRoom.networkWithRetrofit.pojo.ProductsNetworkItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository(private val mProductsDao: ProductsDao) {
    private val retroService = RetrofitClient.getRetrofitClient()

    //    Me traigo toda la tabla
    val allProductsLiveData = mProductsDao.showAllProducts()

    //Vieja confiable
    fun getDataFromServer() {
        val call = retroService.fechAllProducts()
        call.enqueue(object : Callback<List<ProductsNetworkItem>> {


            override fun onResponse(
                call: Call<List<ProductsNetworkItem>>,
                response: Response<List<ProductsNetworkItem>>
            ) {
                when (response.code()) {
                    in 200..299 -> {
                        Log.d("Response", response.body().toString())
                        CoroutineScope(Dispatchers.IO).launch {
                            response.body()?.let {
                                it
                            }

                        }
                    }
                    in 300..399 -> Log.d("Response", response.body().toString())
                    in 400..499 -> Log.d("Response", response.body().toString())
                    in 500..599 -> Log.d("Response", response.body().toString())
                    else -> Log.d("Error", response.body().toString())
                }
            }

            override fun onFailure(call: Call<List<ProductsNetworkItem>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    // corrutinas
    fun getDataFromServerCorroutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { retroService.fetchAllProductsCorroutines() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> {
                    it.body()?.let {
//                            mProductsDao.insertAllProducts(convert(ProductsNetworkItem()))
                    }

                }

                in 300..399 -> Log.d("Response", it.body().toString())
                in 400..499 -> Log.d("Response", it.body().toString())
                in 500..599 -> Log.d("Response", it.body().toString())
                else -> Log.d("Error", it.body().toString())
            }
            service.onFailure {
                Log.e("Error", it.message.toString())
            }
        }

        fun convert(listFromNetwork: ProductsNetwork): List<ProductsEntity> {
            val listMutable = mutableListOf<ProductsEntity>()
            listFromNetwork.map {
                listMutable.add(ProductsEntity(it.id, it.image, it.name, it.price))
            }
            return listMutable
        }
    }
}