package com.lemonade.todoscompose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

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