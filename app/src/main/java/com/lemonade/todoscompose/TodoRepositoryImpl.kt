package com.lemonade.todoscompose

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(): TodoRepository {
    private var counterID: Int = 1
    private val todos: MutableList<Todo> = mutableListOf()
    private val todosFlow: Flow<MutableList<Todo>> = flow {
        while (true) {
            emit(todos.toMutableStateList())
            delay(100)
        }
    }

    override fun fetchAll() = todosFlow

    override suspend fun create(todo: Todo) {
        todo.id = counterID++
        todos.add(todo)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        todos[index].done = selected
    }
}