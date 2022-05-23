package com.lemonade.todoscompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodosListViewModel : ViewModel() {

    private val _todos = MutableLiveData<MutableList<Todo>>(mutableListOf())
    val todos: LiveData<MutableList<Todo>> get() = _todos

    fun loadTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.postValue(
                mutableListOf(
                    Todo(1, "Fix bed", false),
                    Todo(2, "Start project", false),
                    Todo(3, "Go to the supermarket", false)
                )
            )
        }
    }

    fun updateState(index: Int) {
        val item = _todos.value?.get(index)
        item?.done = !item?.done!!

        _todos.value?.set(index, item)

        Log.d("TODO_VALUE", _todos.value.toString())
    }
}