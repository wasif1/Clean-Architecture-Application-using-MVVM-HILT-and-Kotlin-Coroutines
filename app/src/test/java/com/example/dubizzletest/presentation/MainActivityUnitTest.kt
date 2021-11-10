package com.example.dubizzletest.presentation

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode


@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class MainActivityUnitTest : TestCase() {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    override public fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenMainActivityLaunched() {
        assertTrue(true)
    }
}