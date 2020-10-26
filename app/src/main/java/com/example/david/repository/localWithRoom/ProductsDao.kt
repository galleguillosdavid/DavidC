package com.example.david.repository.localWithRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.david.repository.localWithRoom.networkWithRetrofit.Products
import retrofit2.Response

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
//  suspend fun insertAllProducts(mProductsEntity: retrofit2.Response<kotlin.collections.List<com.example.david.repository.localWithRoom.networkWithRetrofit.Products>>)
//  suspend fun insertAllProducts(mProductsEntity: List<Products>)
    fun insertAllProducts(mProductsEntity: List<Products>)

    @Query("SELECT * FROM products_table")
    fun showAllProducts():LiveData<List<Products>>

    @Query("SELECT * FROM products_table WHERE id=:mId")
    fun showOnProductById(mId: Int):LiveData<Products>
}