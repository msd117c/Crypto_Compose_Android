package com.msd.unit_test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class CoroutineTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
}