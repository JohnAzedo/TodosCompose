package com.lemonade.todoscompose

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodosListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

//    private val _todos = MutableLiveData<List<Todo>>(mutableListOf())
//    val todos: LiveData<List<Todo>> get() = _todos

    val todos : LiveData<List<Todo>> = repository.fetchAll().asLiveData()

//    fun loadTodos(): Flow<List<Todo>> {
////        viewModelScope.launch(Dispatchers.IO) {
////            _todos.postValue(
////                mutableListOf(
////                    Todo(1, "Fix bed", false),
////                    Todo(2, "Start project", false),
////                    Todo(3, "Go to the supermarket", false)
////                )
////            )
////        }
//
//        return
//    }

    fun updateState(index: Int, selected: Boolean) {
//        val list = _todos.value?.toMutableList()
//        val item = list?.get(index)?.copy(done = selected)
//        item?.let { list.set(index, item) }
//        _todos.postValue(list)
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
