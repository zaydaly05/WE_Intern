package com.example.pagingdemo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey

@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val products = viewModel.products.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        when (val refreshState = products.loadState.refresh) {

            is LoadState.Loading -> {
                LoadingContent()
            }

            is LoadState.Error -> {
                ErrorContent(
                    message = refreshState.error.message
                        ?: "Something went wrong",
                    onRetry = { products.retry() }
                )
            }

            is LoadState.NotLoading -> {

                if (products.itemCount == 0) {
                    EmptyContent()
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        items(
                            count = products.itemCount,
                            key = products.itemKey { it.id }
                        ) { index ->

                            products[index]?.let { product ->
                                ProductItem(product)
                            }
                        }

                        when (
                            val appendState =
                                products.loadState.append
                        ) {

                            is LoadState.Loading -> {
                                item {
                                    LoadingItem()
                                }
                            }

                            is LoadState.Error -> {
                                item {
                                    AppendErrorItem(
                                        message =
                                            appendState.error.message
                                                ?: "Failed to load more products",
                                        onRetry = {
                                            products.retry()
                                        }
                                    )
                                }
                            }

                            is LoadState.NotLoading -> Unit
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun LoadingItem() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyContent() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No products found",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Unable to load products",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = message,
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Retry")
        }
    }
}

@Composable
private fun AppendErrorItem(
    message: String,
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(message)

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Retry")
        }
    }
}