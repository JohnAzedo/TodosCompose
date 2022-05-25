package com.lemonade.todoscompose

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

//    val todos : LiveData<List<Todo>> = repository.fetchAll().asLiveData()

    private val _todos = MutableLiveData<List<Todo>>(listOf())
    val todos: LiveData<List<Todo>> = _todos

    fun getTodos(){
        viewModelScope.launch {
            Log.d("CALL", "This")
            _todos.postValue(repository.fetchAll())
        }
    }

    fun updateState(index: Int, selected: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.check(index, selected)
        }
    }

    fun createTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            val todo = Todo(text = "Testing", done = false)
            repository.create(todo)
        }
    }
}
