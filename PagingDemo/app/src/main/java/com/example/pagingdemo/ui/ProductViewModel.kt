package com.example.pagingdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pagingdemo.data.repository.ProductRepository

class ProductViewModel(
    repository: ProductRepository
) : ViewModel() {

    val products = repository
        .getProducts()
        .cachedIn(viewModelScope)
}