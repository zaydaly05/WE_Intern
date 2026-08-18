package com.example.pagingdemo

import android.app.Application
import androidx.room.Room
import com.example.pagingdemo.data.local.AppDatabase
import com.example.pagingdemo.data.remote.RetrofitInstance
import com.example.pagingdemo.data.repository.ProductRepository

class PagingDemoApplication : Application() {

    val database: AppDatabase by lazy {

        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "products.db"
        ).build()
    }

    val repository: ProductRepository by lazy {

        ProductRepository(
            database = database,
            api = RetrofitInstance.api
        )
    }
}