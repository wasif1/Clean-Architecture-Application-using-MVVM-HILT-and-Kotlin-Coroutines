package com.example.dubizzletest.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import junit.framework.TestCase
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest : TestCase(){

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
        = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var activityRule2: ActivityTestRule<DetailsActivityJava>
        = ActivityTestRule(DetailsActivityJava::class.java)

    @Test
    fun testSampleRecyclerVisible() {
        Espresso.onView(withId(com.example.dubizzletest.R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.getActivity().getWindow().getDecorView())
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerScroll() {
        // Get total item of RecyclerView
        val recyclerView: RecyclerView =
            activityRule.getActivity().findViewById(com.example.dubizzletest.R.id.recyclerView)
        val itemCount = recyclerView.adapter!!.itemCount
        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(com.example.dubizzletest.R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.getActivity().getWindow().getDecorView())
                )
            )
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    itemCount - 1
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun testCaseForRecyclerClick() {
        Thread.sleep(10000) // delay for api response
        Espresso.onView(ViewMatchers.withId(com.example.dubizzletest.R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun testCaseForRecyclerItemView() {
        Thread.sleep(10000) // delay for api response
        Espresso.onView(ViewMatchers.withId(com.example.dubizzletest.R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        1, Matchers.allOf(
                            ViewMatchers.withId(com.example.dubizzletest.R.id.root), ViewMatchers.isDisplayed()
                        )
                    )
                )
            )
    }

    fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}