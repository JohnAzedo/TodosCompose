package com.lemonade.todoscompose.domain

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun fetchAll(): Flow<List<Todo>>
    suspend fun create(todo: Todo)
    suspend fun check(index: Int, selected: Boolean)
}