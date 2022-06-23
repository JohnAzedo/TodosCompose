package com.lemonade.todoscompose.infra

import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.flow.*

class TodoRepositoryInMemoryImpl: TodoRepository {
    private var counterID: Int = 1
    private var todos = mutableListOf<Todo>()
    private val _todosFlow = MutableSharedFlow<List<Todo>>(0)

    override fun getFlow() = _todosFlow

    override suspend fun create(todo: Todo) {
        todo.id = counterID++
        todos.add(todo)
        _todosFlow.emit(todos)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        val todo = todos.removeAt(index)
        todo.done = selected
        todos.add(index, todo)
        _todosFlow.emit(todos)
    }

    companion object {
        const val TIME_MILLISECONDS: Long = 100
    }
}