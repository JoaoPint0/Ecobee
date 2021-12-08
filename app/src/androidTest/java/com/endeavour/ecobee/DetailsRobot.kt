package com.endeavour.ecobee

fun details(func: DetailsRobot.() -> Unit) = DetailsRobot().apply { func() }

class DetailsRobot : BaseTestRobot() {

    fun setTitle(email: String) = fillEditText(R.id.task_title, email);

    fun setDescription(pass: String) = fillEditText(R.id.task_description, pass)

    fun clickSave() = clickButton(R.id.task_save_btn)

}