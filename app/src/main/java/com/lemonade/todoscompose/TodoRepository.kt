package com.lemonade.todoscompose

interface TodoRepository {
    fun fetchAll(): List<Todo>
    suspend fun create(todo: Todo)
    suspend fun check(index: Int, selected: Boolean)
}