package com.example.david.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.david.repository.localWithRoom.ProductsDao
import com.example.david.repository.localWithRoom.networkWithRetrofit.Products
import com.example.david.repository.localWithRoom.networkWithRetrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsRepository(private val mProductsDao: ProductsDao) {
    private val retroService = RetrofitClient.getRetrofitClient()

    //    Me traigo toda la tabla
    val allProductsLiveData = mProductsDao.showAllProducts()

    fun getProductById(id: Int): LiveData<Products>{
        return mProductsDao.showOnProductById(id)
    }


    // corrutinas R1 T 02:57:50
    fun getDataFromServerCorroutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { retroService.fetchAllProductsCorroutines() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 ->it.body()?.let {
                    mProductsDao.insertAllProducts(it)
                }//c8 t3:39:00
                in 300..399 -> Log.d("Response", it.body().toString())
                in 400..499 -> Log.d("Response", it.body().toString())
                in 500..599 -> Log.d("Response", it.body().toString())
                else -> Log.d("Error", it.body().toString())
            }
            service.onFailure {
                Log.e("Error", it.message.toString())
            }
        }
    }
//    fun converters(listFromNetwork: List<Products>): List<SuperHeroesEntity> {
//        val listMutable = mutableListOf<SuperHeroesEntity>()
//
//        listFromNetwork.map {
//            listMutable.add(
//                    SuperHeroesEntity(
//                            it.id,
//                            it.images.xs,
//                            it.images.lg,
//                            it.name,
//                            it.biography.alterEgos,
//                            it.appearance.height,
//                            it.appearance.eyeColor,
//                            it.biography.aliases
//                    )
//            )
//        }
//        return listMutable
}