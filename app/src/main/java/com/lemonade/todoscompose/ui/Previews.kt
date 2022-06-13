package com.lemonade.todoscompose.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.lemonade.todoscompose.domain.Todo

object Previews {
    class ToolbarTitleProvider : PreviewParameterProvider<String> {
        override val values: Sequence<String>
            get() = sequenceOf("ToolBar app")
    }

    class TodoRowProvider : PreviewParameterProvider<Todo> {
        override val values: Sequence<Todo>
            get() = sequenceOf(Todo(1, "Fix bed", false))
    }
}