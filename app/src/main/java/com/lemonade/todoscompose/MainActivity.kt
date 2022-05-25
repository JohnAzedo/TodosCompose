package com.lemonade.todoscompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: TodosListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTodos()
        setContent {
            TodosListPage()
        }
    }

    @Preview
    @Composable
    fun TodosListPage() {
        val todos: List<Todo> by viewModel.todos.observeAsState(listOf())

        Scaffold(
            topBar = { Toolbar(title = "Just do it!") }
        ) { 
            TodosList(todos)
            Button(onClick = { viewModel.getTodos() }) {
                Text("Create")
            }
        }
    }

    @Composable
    fun TodosList(todos: List<Todo>){
        Log.d("CALL", todos.toString())

        todos.let {
            LazyColumn {
                items(it.size) { index ->
                    TodoRow(index, it[index])
                }
            }
        }
        
    }

    @Composable
    fun TodoRow(index: Int, todo: Todo) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("TODO_VALUE", "UI -> ${todo.id} - ${todo.done}")
            Checkbox(checked = todo.done, onCheckedChange = { viewModel.updateState(index, it) })
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
