package com.lemonade.todoscompose.domain

data class Todo(
    var id: Int? = null,
    val text: String,
    var done: Boolean
)