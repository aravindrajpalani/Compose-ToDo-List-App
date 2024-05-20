package com.aravind.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListScreen()
        }
    }
}

@Composable
fun ToDoListScreen(modifier: Modifier = Modifier, toDoViewModel: ToDoViewModel = viewModel()) {
    Column {
        ToDoList(
            toDoList = toDoViewModel.toDoList,
            onChecked = { toDoItem, isChecked ->
                toDoViewModel.onTaskChecked(
                    toDoItem, isChecked
                )
            },
            onDeleteTask = { toDoItem -> toDoViewModel.removeToDoItem(toDoItem) },
            modifier = modifier
                .weight(1f)
                .fillMaxSize(),
        )
        Row {
            var toDoText by remember {
                mutableStateOf("")
            }
            TextField(
                value = toDoText,
                onValueChange = { text -> toDoText = text },
                modifier = Modifier.weight(1f)
            )
            IconButton(modifier = Modifier.then(Modifier.size(48.dp)), onClick = {

                toDoViewModel.addToDoItem(newTaskName = toDoText)
                toDoText = ""
            }) {
                Icon(
                    Icons.Filled.Check, "add button", tint = Color.Red
                )
            }
        }
    }
}

@Composable
fun ToDoList(
    toDoList: List<ToDo>,
    onChecked: (ToDo, Boolean) -> Unit,
    onDeleteTask: (ToDo) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(state = rememberLazyListState(), modifier = modifier) {
        items(toDoList, key = { it.taskName }) { toDoItem ->
            ToDoItem(
                taskName = toDoItem.taskName,
                isCompleted = toDoItem.isCompleted.value,
                onChecked = { isChecked -> onChecked(toDoItem, isChecked) },
                onDeleteClick = { onDeleteTask(toDoItem) },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ToDoItem(
    taskName: String,
    isCompleted: Boolean,
    onChecked: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(12.dp)
    ) {
        Checkbox(checked = isCompleted, onCheckedChange = onChecked)
        Text(text = taskName, modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.then(Modifier.size(48.dp)), onClick = onDeleteClick
        ) {
            Icon(
                Icons.Filled.Delete, "delete button", tint = Color.Red
            )
        }
    }
}


