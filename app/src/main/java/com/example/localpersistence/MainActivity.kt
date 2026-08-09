package com.example.localpersistence


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.localpersistence.data.local.database.AppDatabase
import com.example.localpersistence.data.repository.TodoRepository
import com.example.localpersistence.data.local.viewmodel.TodoViewModel
import com.example.localpersistence.data.local.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todo_database"
        ).build()
    }

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            TodoRepository(database.todoDao())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var text by remember {
                mutableStateOf("")
            }

            val todos by viewModel.todos.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    label = {
                        Text("Todo")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (text.isNotBlank()) {
                            viewModel.insert(text)
                            text = ""
                        }
                    }
                ) {
                    Text("Add Todo")
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {

                    items(todos) { todo ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {

                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(todo.title)

                                Button(
                                    onClick = {
                                        viewModel.delete(todo)
                                    }
                                ) {
                                    Text("Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
