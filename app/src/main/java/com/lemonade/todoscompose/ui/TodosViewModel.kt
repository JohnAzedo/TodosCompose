package com.lemonade.todoscompose.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodosViewModel(
    private val repository: TodoRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        launch {
            repository.getFlow()
                .catch { /* Do something */ }
                .collect {
                    _todos.postValue(it)
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
        viewModelScope.launch(dispatcher) {
            callback()
        }
    }
}