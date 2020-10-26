package com.example.david.repository.localWithRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.david.repository.localWithRoom.networkWithRetrofit.Products

@Database(entities = [Products::class],version = 1)
abstract class ProductsDataBase: RoomDatabase() {
    //Dao
    abstract fun mProductsDao(): ProductsDao

    companion object {
        @Volatile
        private var INSTANCE : ProductsDataBase? = null

        fun getDatabase(context: Context): ProductsDataBase{
            val tempInstance= INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context,
                    ProductsDataBase::class.java,
                    "productsDb")
                    .build()
                INSTANCE= instance
                return instance
            }
        }
    }
}