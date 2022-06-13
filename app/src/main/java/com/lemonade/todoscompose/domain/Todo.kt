package com.lemonade.todoscompose.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Todo(
    var id: Int? = null,
    val text: String,
    var done: Boolean
)