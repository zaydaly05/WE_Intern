package com.example.hitplugin.data


import com.example.hitplugin.database.UserDao
import com.example.hitplugin.database.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    suspend fun getUsers(): List<UserEntity> {

        return try {

            val usersFromApi = apiService.getUsers()

            val users = usersFromApi.map { user ->
                UserEntity(
                    id = user.id,
                    name = "${user.firstName} ${user.lastName}",
                    email = user.email
                )
            }

            userDao.insertUsers(users)

            users

        } catch (exception: Exception) {

            userDao.getUsers()
        }
    }
}