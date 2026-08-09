package com.example.coroutinesandrooms.viewmodel
import com.example.coroutinesandrooms.model.Post
import com.example.coroutinesandrooms.network.RetrofitInstance
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        loadPosts()
    }

    private fun loadPosts() {

        viewModelScope.launch {

            try {

                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getPosts()
                }

                _posts.value = response.posts

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }
    }
}