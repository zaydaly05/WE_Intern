package com.example.backgroundsync.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts ORDER BY id ASC")
    fun observePosts(): Flow<List<PostEntity>>

    @Upsert
    suspend fun upsertPosts(posts: List<PostEntity>)

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}