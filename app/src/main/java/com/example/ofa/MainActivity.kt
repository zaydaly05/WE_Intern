package com.example.ofa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.ofa.data.local.AppDatabase
import com.example.ofa.data.repository.UserRepository
import com.example.ofa.ui.UserScreen
import com.example.ofa.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "users.db"
        ).build()

        val repository = UserRepository(db.userDao())

        val viewModel = UserViewModel(repository)

        setContent {

            UserScreen(viewModel)

        }
    }
}