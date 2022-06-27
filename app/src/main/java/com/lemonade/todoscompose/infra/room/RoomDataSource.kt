package com.lemonade.todoscompose.infra.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDataSource {
    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTodo(todo: TodoEntity)

    @Query("UPDATE todos SET done = :selected WHERE id = :index")
    suspend fun checkTodo(index: Int, selected: Boolean)

}