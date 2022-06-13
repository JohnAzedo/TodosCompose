package com.lemonade.todoscompose.infra

import androidx.compose.runtime.mutableStateListOf
import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TodoRepositoryInMemoryImpl @Inject constructor(): TodoRepository {
    private var counterID: Int = 1
    private var todos = mutableStateListOf<Todo>()
    private val _flow = flow {
        while (true) {
            emit(todos)
            delay(TIME_MILLISECONDS)
        }
    }

    override fun fetchAll() = _flow

    override suspend fun create(todo: Todo) {
        todo.id = counterID++
        todos.add(todo)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        val todo = todos.removeAt(index)
        todo.done = selected
        todos.add(index, todo)
    }

    companion object {
        const val TIME_MILLISECONDS: Long = 100
    }
}