package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyUiAutomatorTest {

    private lateinit var device: UiDevice
    private val challenges = listOf("Challenge 1: Accessibility", "Challenge 2: Localization", "Challenge 3: Performance", "Challenge 4: Multi-device support", "Challenge 5: Security")


    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 5000)

        // Launch the app
        val context = InstrumentationRegistry.getInstrumentation().context
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(context.packageName).depth(0)), 5000)
    }

    @Test
    fun testStartActivityExplicitlyAndCheckChallenge() {
        // Click the "start activity explicitly" button
        device.findObject(By.text("Start Activity Explicitly")).click()

        // Wait for the new activity to appear
        device.wait(Until.hasObject(By.pkg("com.example.myapplication").depth(0)), 5000)

        // Check if any of the challenges are displayed
        val foundChallenge = challenges.any { challenge ->
            device.hasObject(By.text(challenge))
        }

        assertTrue("Expected to find at least one challenge text", foundChallenge)
    }
}
