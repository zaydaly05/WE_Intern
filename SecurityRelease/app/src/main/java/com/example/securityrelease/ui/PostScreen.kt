package com.example.securityrelease.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.securityrelease.data.RetrofitInstance
import com.example.securityrelease.data.model.Post
import kotlinx.coroutines.launch

@Composable
fun PostScreen() {

    var post by remember {
        mutableStateOf<Post?>(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Day 10",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Security & Code Shrinking",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "R8 + ProGuard + JSON",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            modifier = Modifier.padding(top = 24.dp),

            enabled = !isLoading,

            onClick = {

                scope.launch {

                    isLoading = true
                    errorMessage = null

                    try {

                        val result =
                            RetrofitInstance.api.getPost()

                        post = result

                    } catch (exception: Exception) {

                        errorMessage =
                            exception.message
                                ?: "Unable to load data"

                    } finally {

                        isLoading = false
                    }
                }
            }
        ) {

            Text(
                text = "Fetch API Data"
            )
        }

        if (isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp)
            )
        }

        post?.let { currentPost ->

            Card(
                modifier = Modifier
                    .padding(top = 24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Post ID: ${currentPost.id}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "User ID: ${currentPost.userId}"
                    )

                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = currentPost.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = currentPost.body
                    )
                }
            }
        }

        errorMessage?.let { error ->

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Error: $error"
            )
        }
    }
}