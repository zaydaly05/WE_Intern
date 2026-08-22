package com.example.taskflow.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskflow.domain.repository.UserRepository
import com.example.taskflow.domain.usecase.GetUsersUseCase

class UserViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {

            val useCase = GetUsersUseCase(repository)

            @Suppress("UNCHECKED_CAST")
            return UserViewModel(useCase) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}

