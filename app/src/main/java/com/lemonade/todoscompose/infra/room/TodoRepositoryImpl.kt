package com.lemonade.todoscompose.infra.room

import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl: TodoRepository {
    override fun getFlow(): Flow<List<Todo>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun check(index: Int, selected: Boolean) {
        TODO("Not yet implemented")
    }
}