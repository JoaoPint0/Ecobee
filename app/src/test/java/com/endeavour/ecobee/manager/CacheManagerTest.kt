package com.endeavour.ecobee.manager

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.endeavour.ecobee.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CacheManagerTest{

    companion object{
        const val SOME_STRING = "SOME_STRING"
        const val STRING_UPDATED = "STRING_UPDATED"
    }

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun createTask() = runBlocking {

        val cacheManager = CacheManager()

        val task = Task(
            id = "1",
            title = SOME_STRING,
            description = SOME_STRING,
            year = SOME_STRING,
            month = SOME_STRING,
            day = SOME_STRING
        )

        cacheManager.saveTask(task)

        Assert.assertEquals(listOf(task), cacheManager.getTasks().firstOrNull())
    }

    @Test
    fun updateTask() = runBlocking {

        val cacheManager = CacheManager()

        val task = Task(
            id = "1",
            title = SOME_STRING,
            description = SOME_STRING,
            year = SOME_STRING,
            month = SOME_STRING,
            day = SOME_STRING
        )

        cacheManager.saveTask(task)

        val task2 = task.copy(title = STRING_UPDATED)
        cacheManager.saveTask(task2)

        Assert.assertEquals(listOf(task2), cacheManager.getTasks().firstOrNull())
    }

    @Test
    fun createMultipleTask() = runBlocking {

        val cacheManager = CacheManager()

        val task = Task(
            id = "1",
            title = SOME_STRING,
            description = SOME_STRING,
            year = SOME_STRING,
            month = SOME_STRING,
            day = SOME_STRING
        )

        cacheManager.saveTask(task)

        val task2 = task.copy(id = "2")
        cacheManager.saveTask(task2)

        Assert.assertEquals(listOf(task, task2), cacheManager.getTasks().firstOrNull())
    }
}