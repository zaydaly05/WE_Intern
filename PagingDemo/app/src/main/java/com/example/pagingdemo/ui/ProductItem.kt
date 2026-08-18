package com.example.pagingdemo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagingdemo.data.local.ProductEntity

@Composable
fun ProductItem(
    product: ProductEntity
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 6.dp
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "#${product.id}",
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}