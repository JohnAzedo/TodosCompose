package com.lemonade.todoscompose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemonade.todoscompose.domain.Todo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: TodosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodosListPage()
        }
    }

    @Preview
    @Composable
    fun TodosListPage() {
        Scaffold(
            topBar = { Toolbar(title = "Just do it!") },
            floatingActionButton = {
                FloatingActionButton(onClick = { viewModel.createTodo() }) {
                    Icon(Icons.Filled.Add, "")
                }
            }
        ) {
            TodosList()
        }
    }

    @Composable
    fun TodosList() {
        val todos by viewModel.todos.observeAsState(initial = listOf())

        todos.let {
            LazyColumn {
                items(it.size) { index ->
                    TodoRow(it[index])
                }
            }
        }

    }

    @Composable
    fun TodoRow(todo: Todo) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = todo.done, onCheckedChange = {
                todo.id?.let { id ->
                    viewModel.updateState(id, it)
                }
            })
            Text(
                text = todo.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }


    @Preview
    @Composable
    fun Toolbar(@PreviewParameter(Previews.ToolbarTitleProvider::class) title: String) {
        return TopAppBar(
            title = { Text(text = title) },
            backgroundColor = Color.White,
            elevation = 0.dp
        )
    }
}
