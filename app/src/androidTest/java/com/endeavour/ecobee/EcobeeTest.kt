package com.endeavour.ecobee

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EcobeeTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createAndUpdateTask() {

        val title = "TITLE"
        val description = "DESCRIPTION"

        main {
            createTask()
        }

        details {
            setTitle(title)
            setDescription(description)
            clickSave()
        }

        main {
            checkTaskTitle(title)
            checkTaskDescription(description)
            clickTask(0)
        }

        val titleUpdated = "TITLE_UPDATED"
        val descriptionUpdated = "DESCRIPTION_UPDATED"

        details {
            setTitle(titleUpdated)
            setDescription(descriptionUpdated)
            clickSave()
        }

        main {
            checkTaskTitle(titleUpdated)
            checkTaskDescription(descriptionUpdated)
        }
    }

}