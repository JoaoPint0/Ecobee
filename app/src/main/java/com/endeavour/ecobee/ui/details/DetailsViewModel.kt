package com.endeavour.ecobee.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeavour.ecobee.manager.CacheManager
import com.endeavour.ecobee.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val cacheManager: CacheManager
) : ViewModel() {

    private var taskId = UUID.randomUUID().toString()
    private val calendar = Calendar.getInstance()

    val editableTitle = MutableLiveData<String>()
    val editableDescription = MutableLiveData<String>()

    var editableYear: MutableLiveData<Int> = MutableLiveData(calendar.get(Calendar.YEAR))
    var editableMonth: MutableLiveData<Int> = MutableLiveData(calendar.get(Calendar.MONTH))
    var editableDay: MutableLiveData<Int> = MutableLiveData(calendar.get(Calendar.DAY_OF_MONTH))

    fun editTask(task: Task) {
        taskId = task.id
        editableTitle.value = task.title
        editableDescription.value = task.description
        editableYear.value = task.year.toInt()
        editableMonth.value = task.month.toInt() - 1
        editableDay.value = task.day.toInt()
    }

    fun saveTask() {
        val task = Task(
            id = taskId,
            title = editableTitle.value.orEmpty(),
            description = editableDescription.value.orEmpty(),
            year = editableYear.value.toString(),
            month = editableMonth.value?.plus(1).toString(),
            day = editableDay.value.toString()
        )

        cacheManager.saveTask(task)
    }
}