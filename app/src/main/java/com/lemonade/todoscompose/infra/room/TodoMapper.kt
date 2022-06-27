package com.lemonade.todoscompose.infra.room

import com.lemonade.todoscompose.domain.Todo

object TodoMapper {
    fun toRoom(todo: Todo): TodoEntity {
        return TodoEntity(
            id = todo.id,
            text = todo.text,
            done = todo.done
        )
    }

    fun fromRoom(todoEntity: TodoEntity): Todo {
        return Todo(
            id = todoEntity.id,
            text = todoEntity.text,
            done = todoEntity.done
        )
    }
}