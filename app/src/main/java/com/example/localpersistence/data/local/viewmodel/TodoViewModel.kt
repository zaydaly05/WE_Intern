package com.example.localpersistence.data.local.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localpersistence.data.local.entity.TodoEntity
import com.example.localpersistence.data.repository.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    val todos: StateFlow<List<TodoEntity>> =
        repository.allTodos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insert(title: String) {
        viewModelScope.launch {
            repository.insert(
                TodoEntity(
                    title = title,
                    completed = false
                )
            )
        }
    }

    fun delete(todo: TodoEntity) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }

    fun update(todo: TodoEntity) {
        viewModelScope.launch {
            repository.update(todo)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}