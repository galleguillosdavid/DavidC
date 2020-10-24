package com.example.david.repository.localWithRoom

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class ProductsEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val image: String,
    val name: String,
    val price: Int
)