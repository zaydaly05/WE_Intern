package com.example.backgroundsync.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.backgroundsync.core.database.AppDatabase
import com.example.backgroundsync.core.network.RetrofitClient
import com.example.backgroundsync.data.PostRepository

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(
    appContext,
    workerParams
) {

    override suspend fun doWork(): Result {

        return try {

            val database = AppDatabase.getInstance(applicationContext)

            val repository = PostRepository(
                apiService = RetrofitClient.apiService,
                postDao = database.postDao()
            )

            repository.syncPosts()

            Result.success()

        } catch (exception: Exception) {

            Result.retry()
        }
    }
}