package com.example.david.viewModel

import androidx.lifecycle.ViewModel
import com.example.david.repository.ProductsRepository

class ProductsViewModel: ViewModel() {

    private val mRepository: ProductsRepository

    init {
        mRepository= ProductsRepository()
    }
}