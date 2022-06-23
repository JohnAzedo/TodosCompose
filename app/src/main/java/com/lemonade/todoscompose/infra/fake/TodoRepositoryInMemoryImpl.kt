package com.lemonade.todoscompose.infra.fake

import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.flow.flow

class TodoRepositoryInMemoryImpl: TodoRepository {
    private var counterID: Int = 1
    private var todos = mutableListOf<Todo>()

    override fun getFlow() = flow {
        emit(todos)
    }

    override suspend fun create(todo: Todo) {
        todo.id = counterID++
        todos.add(todo)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        val todo = todos.removeAt(index)
        todo.done = selected
        todos.add(index, todo)
    }
}