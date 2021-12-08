package com.endeavour.ecobee.manager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.endeavour.ecobee.models.Task


class CacheManager {

    val taskObservable = MutableLiveData<List<Task>>()
    val tasks = mutableMapOf<String, Task>()

    fun saveTask(task: Task){
        tasks[task.id] = task
        taskObservable.value = tasks.values.toList()
    }

    fun getTasks() = taskObservable.asFlow()
}