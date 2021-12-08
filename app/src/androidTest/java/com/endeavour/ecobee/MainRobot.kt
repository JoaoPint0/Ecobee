package com.endeavour.ecobee

fun main(func: MainRobot.() -> Unit) = MainRobot().apply { func() }

class MainRobot : BaseTestRobot() {

    fun createTask() = clickButton(R.id.float_button)

    fun clickTask(position: Int) = clickListItem(R.id.tasks_list, position)

    fun checkTaskTitle(text: String) = checkListItem(text)

    fun checkTaskDescription(text: String) = checkListItem(text)

}