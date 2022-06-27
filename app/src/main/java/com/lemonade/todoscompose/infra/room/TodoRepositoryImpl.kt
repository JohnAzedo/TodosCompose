package com.lemonade.todoscompose.infra.room

import com.lemonade.todoscompose.domain.Todo
import com.lemonade.todoscompose.domain.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dataSource: RoomDataSource
): TodoRepository {
    override fun getFlow(): Flow<List<Todo>> {
        return dataSource.getAll().map { list ->
            list.map {
                TodoMapper.fromRoom(it)
            }
        }
    }

    override suspend fun create(todo: Todo) {
        val entity = TodoMapper.toRoom(todo)
        dataSource.createTodo(entity)
    }

    override suspend fun check(index: Int, selected: Boolean) {
        dataSource.checkTodo(index, selected)
    }
}