package com.lemonade.todoscompose

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.fetchAll()

    fun updateState(index: Int, selected: Boolean) = launch {
        repository.check(index, selected)
    }

    fun createTodo() = launch {
        val todo = Todo(text = "Testing", done = false)
        repository.create(todo)
    }

    private fun launch(callback: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            callback()
        }
    }
}