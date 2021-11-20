package com.faztbit.reignapp.ui.main

import com.faztbit.reignapp.base.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun test_any_rule() = coroutineTestRule.testDispatcher.runBlockingTest {
        Assert.assertTrue(true)
    }
}