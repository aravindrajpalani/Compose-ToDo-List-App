package com.aravind.todolistapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    private val _toDoList = getAllToDoList().toMutableStateList()
    val toDoList: List<ToDo>
        get() = _toDoList

    fun onTaskChecked(toDoItem: ToDo, isChecked: Boolean) {
        _toDoList.find { it.taskName.equals(toDoItem.taskName) }
            ?.let { it.isCompleted.value = isChecked }
    }

    fun addToDoItem(newTaskName: String) {
        if (newTaskName.isNotEmpty() && _toDoList.any { it.taskName.equals(newTaskName) }.not()) {
            _toDoList.add(ToDo(taskName = newTaskName))
        }
    }

    fun removeToDoItem(toDo: ToDo) {
        _toDoList.remove(toDo)
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


}