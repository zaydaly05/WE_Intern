package com.example.pagingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.pagingdemo.ui.ProductScreen
import com.example.pagingdemo.ui.ProductViewModel
import com.example.pagingdemo.ui.ProductViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(
            (application as PagingDemoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ProductScreen(
                viewModel = viewModel
            )
        }
    }
}