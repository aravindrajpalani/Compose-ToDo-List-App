package com.aravind.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aravind.todolistapp.ui.theme.ToDoListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ToDoList()
                }
            }
        }
    }
}

@Composable
fun ToDoList(
    modifier: Modifier = Modifier, toDoList: List<ToDo> = remember {
        getAllToDoList()
    }
) {
    LazyColumn(modifier = modifier) {
        items(toDoList) { toDoItem ->
            ToDoItem(
                taskName = toDoItem.taskName
            )
        }
    }
}

@Composable
fun ToDoItem(
    taskName: String, modifier: Modifier = Modifier
) {
    var isCompleted by remember { mutableStateOf(false) }
    ToDoItem(
        taskName = taskName,
        isCompleted = isCompleted,
        onCheckedChange = { newValue -> isCompleted = newValue },
        onDelete = {},
        modifier = modifier
    )
}

@Composable
fun ToDoItem(
    taskName: String,
    isCompleted: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(12.dp)
    ) {
        Checkbox(checked = isCompleted, onCheckedChange = onCheckedChange)
        Text(text = taskName, modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.then(Modifier.size(48.dp)), onClick = onDelete
        ) {
            Icon(
                Icons.Filled.Delete, "delete button", tint = Color.Red
            )
        }
    }
}

private fun getAllToDoList(): List<ToDo> {
    return listOf(
        ToDo(taskName = "Do exercise"),
        ToDo(taskName = "Go walking"),
        ToDo(taskName = "Read book"),
        ToDo(taskName = "Finish task"),
        ToDo(taskName = "Complete project"),
        ToDo(taskName = "Write assignment"),
        ToDo(taskName = "Publish article"),
        ToDo(taskName = "Do Task A"),
        ToDo(taskName = "Do Task B"),
        ToDo(taskName = "Do Task C"),
        ToDo(taskName = "Do Task D"),
        ToDo(taskName = "Do Task E"),
        ToDo(taskName = "Do Task F"),
    )
}