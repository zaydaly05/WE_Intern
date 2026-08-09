package com.example.localpersistence.data.repository

import com.example.localpersistence.data.local.dao.TodoDao
import com.example.localpersistence.data.local.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val todoDao: TodoDao
) {

    val allTodos: Flow<List<TodoEntity>> =
        todoDao.getAllTodos()

    suspend fun insert(todo: TodoEntity) {
        todoDao.insertTodo(todo)
    }

    suspend fun update(todo: TodoEntity) {
        todoDao.updateTodo(todo)
    }

    suspend fun delete(todo: TodoEntity) {
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteAll() {
        todoDao.deleteAll()
    }
}