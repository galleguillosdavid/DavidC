package com.example.david.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.david.repository.ProductsRepository
import com.example.david.repository.localWithRoom.ProductsDao
import com.example.david.repository.localWithRoom.ProductsDataBase
import com.example.david.repository.localWithRoom.ProductsEntity

class ProductsViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository: ProductsRepository
    val livedataFromLocal: LiveData<List<ProductsEntity>>
    init {
        val productsDao= ProductsDataBase.getDatabase(application).mProductsDao()
        mRepository= ProductsRepository(productsDao)
        mRepository.getDataFromServer()
        livedataFromLocal= mRepository.allProductsLiveData
    }

}