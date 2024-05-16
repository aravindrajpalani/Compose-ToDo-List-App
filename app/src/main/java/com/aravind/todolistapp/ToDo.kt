package com.aravind.todolistapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


data class ToDo(
    var taskName: String,
    var isCompleted: MutableState<Boolean> = mutableStateOf(false)
)
