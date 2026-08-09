package com.example.composefeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Post(
    val user: String,
    val time: String,
    val message: String,
    val likes: Int,
    val comments: Int
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashboardScreen()
        }
    }
}

@Composable
fun DashboardScreen() {

    val posts = listOf(
        Post(
            "John Smith",
            "2 min ago",
            "Learning Jetpack Compose today!",
            120,
            15
        ),
        Post(
            "Emma",
            "10 min ago",
            "Flutter is awesome ❤️",
            88,
            9
        ),
        Post(
            "Michael",
            "20 min ago",
            "Compose feels very declarative.",
            150,
            32
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {

        item {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF2196F3),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(20.dp)
            ) {

                Column {

                    Text(
                        "Welcome Back",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "You have 18 new notifications",
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Recent Posts",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(posts) { post ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                post.user,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Text(post.time)
                        }

                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        post.message,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color.Red
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(post.likes.toString())
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.Comment,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(post.comments.toString())
                        }

                        Icon(
                            Icons.Default.Share,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}