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

    fun updateState(index: Int, selected: Boolean) {
        val oldValue = _todos.value?.toMutableList()
        val oldItem = oldValue?.get(index)?.copy()
        oldItem?.done = selected
        oldItem?.let {
            oldValue.removeAt(index)
            oldValue.add(index, oldItem)
        }
        _todos.postValue(oldValue)
    }
}
