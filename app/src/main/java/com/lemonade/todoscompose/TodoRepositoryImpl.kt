package com.lemonade.todoscompose

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepositoryImpl: TodoRepository{
    private var counterID: Int = 1
    private val todos: MutableList<Todo> = mutableListOf()

    override fun fetchAll(): Flow<List<Todo>> {
        return flow { todos }
    }

    override suspend fun create(todo: Todo) {
        todo.id = counterID++
        todos.add(todo)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        todos[index].done = selected
    }
}