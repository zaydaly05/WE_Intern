package com.example.backgroundsync.data

import com.example.backgroundsync.core.database.PostDao
import com.example.backgroundsync.core.database.PostEntity
import com.example.backgroundsync.core.network.ApiService
import com.example.backgroundsync.core.network.toEntity
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val apiService: ApiService,
    private val postDao: PostDao
) {

    fun observePosts(): Flow<List<PostEntity>> {
        return postDao.observePosts()
    }

    suspend fun syncPosts() {

        val postsFromApi = apiService.getPosts()

        val entities = postsFromApi.map {
            it.toEntity()
        }

        postDao.upsertPosts(entities)
    }
}