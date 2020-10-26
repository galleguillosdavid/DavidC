package com.example.david.repository.localWithRoom.networkWithRetrofit


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class Products(
        @PrimaryKey
        val id: Int,
        val image: String,
        val name: String,
        val price: Int
)