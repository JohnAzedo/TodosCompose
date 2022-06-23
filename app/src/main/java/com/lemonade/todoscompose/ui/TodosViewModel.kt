package com.lemonade.todoscompose.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodosViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = mutableStateListOf<Todo>()

    init {
        launch {
            repository.getFlow().collect {
                todos.clear()
                todos.addAll(it)
            }
        }
    }

    fun createTodo() = launch {
        val todo = Todo(text = "Testing", done = false)
        repository.create(todo)
    }

    fun updateState(index: Int, selected: Boolean) = launch {
        repository.check(index, selected)
    }

    private fun launch(callback: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            callback()
        }
    }
}