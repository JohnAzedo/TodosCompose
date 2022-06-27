package com.lemonade.todoscompose.infra.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey val id: Int?,
    @NonNull val text: String,
    @NonNull val done: Boolean
)