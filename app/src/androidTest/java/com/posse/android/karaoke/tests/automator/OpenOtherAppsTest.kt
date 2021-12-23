package com.posse.android.karaoke.tests.automator

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class OpenOtherAppsTest {

    @Test
    fun test_OpenSettings() {
        uiDevice.pressHome()
        uiDevice.swipe(200, 800, 200, 300, 5)

        //val allAppsButton: UiObject = uiDevice.findObject(UiSelector().description("Apps"))
        //allAppsButton.clickAndWaitForNewWindow()

        //val appsTab: UiObject = uiDevice.findObject(UiSelector().text("Apps"))
        //appsTab.click()

        val appViews = UiScrollable(UiSelector().scrollable(false))
        //appViews.setAsHorizontalList()

        val settingsApp =
            appViews.getChildByText(UiSelector().className(TextView::class.java.name), SETTINGS)

        settingsApp.clickAndWaitForNewWindow()

        val settingsValidation =
            uiDevice.findObject(UiSelector().packageName(SETTINGS_PACKAGE))

        Assert.assertTrue(settingsValidation.exists())
    }
}
