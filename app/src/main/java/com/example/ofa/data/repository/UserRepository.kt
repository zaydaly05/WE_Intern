package com.example.ofa.data.repository


import com.example.ofa.data.local.UserDao
import com.example.ofa.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.example.ofa.data.local.UserEntity

class UserRepository(
    private val dao: UserDao
) {

    val users: Flow<List<UserEntity>> =
        dao.getUsers()

    suspend fun refreshUsers() {

        withContext(Dispatchers.IO) {

            try {

                val networkUsers =
                    RetrofitInstance.api.getUsers()

                dao.insertUsers(networkUsers)

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }
    }
}