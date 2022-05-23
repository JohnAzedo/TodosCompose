package com.lemonade.todoscompose

data class Todo(
    val id: Int,
    val text: String,
    var done: Boolean
)