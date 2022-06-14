package com.lemonade.todoscompose.domain


import kotlinx.coroutines.flow.SharedFlow

interface TodoRepository {
    fun getFlow(): SharedFlow<List<Todo>>
    suspend fun create(todo: Todo)
    suspend fun check(index: Int, selected: Boolean)
}