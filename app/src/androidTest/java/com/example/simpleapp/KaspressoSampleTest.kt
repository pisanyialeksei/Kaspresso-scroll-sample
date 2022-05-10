package com.example.simpleapp

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class KaspressoSampleTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun sampleTest() {
        run {
            activityTestRule.launchActivity(null)
            step("Click on '1'") {
                SampleScreen.firstView.click()
            }

            step("Click on '2'") {
                SampleScreen.secondView.click()
            }

            step("Click on '3'") {
                SampleScreen.thirdView.click()
            }

            step("Click on '4'") {
                SampleScreen.fourthView.click()
            }

            step("Click on '5'") {
                SampleScreen.fifthView.click()
            }
        }
    }
}