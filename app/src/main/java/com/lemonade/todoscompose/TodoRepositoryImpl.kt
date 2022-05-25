package com.lemonade.todoscompose
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(): TodoRepository{
    private var counterID: Int = 1
    private val todos: MutableList<Todo> = mutableListOf()

    override fun fetchAll(): List<Todo> {
        return todos
    }

    override suspend fun create(todo: Todo) {
        todo.id = counterID++
        todos.add(todo)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        todos[index].done = selected
    }
}