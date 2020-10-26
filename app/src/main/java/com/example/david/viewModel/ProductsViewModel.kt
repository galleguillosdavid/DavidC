package com.example.david.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.david.repository.ProductsRepository
import com.example.david.repository.localWithRoom.ProductsDataBase
import com.example.david.repository.localWithRoom.networkWithRetrofit.Products

class ProductsViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository: ProductsRepository
    val livedataFromLocal: LiveData<List<Products>>
    init {
        val productsDao= ProductsDataBase.getDatabase(application).mProductsDao()
        mRepository= ProductsRepository(productsDao)
//        mRepository.getDataFromServer()
        mRepository.getDataFromServerCorroutines()
        livedataFromLocal= mRepository.allProductsLiveData
    }

    fun getProductById(id: Int): LiveData<Products>{
        return mRepository.getProductById(id)
    }

}