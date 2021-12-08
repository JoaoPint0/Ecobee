package com.endeavour.ecobee.models

import java.io.Serializable

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val year: String,
    val month: String,
    val day: String
): Serializable
