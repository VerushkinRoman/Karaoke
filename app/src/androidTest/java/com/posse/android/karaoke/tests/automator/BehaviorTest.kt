package com.posse.android.karaoke.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.posse.android.karaoke.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BehaviorTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val container = uiDevice.findObject(By.res(packageName, "container"))
        Assert.assertNotNull(container)
    }

    @Test
    fun test_SearchIsPositive() {
        val text = uiDevice.wait(Until.findObject(By.res(packageName, "songCaption")), TIMEOUT)
        Assert.assertNotNull(text)
    }

    @Test
    fun test_OpenSongScreen() {
        val songsText = uiDevice.wait(Until.findObject(By.res(packageName, "songCaption")), TIMEOUT)
        songsText.click()
        val songDescription = uiDevice.wait(Until.findObject(By.res(packageName, "songCaption")), TIMEOUT)
        Assert.assertNotNull(songDescription)
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}
