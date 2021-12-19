package com.posse.android.karaoke.tests.automator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

internal val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

internal val packageName = ApplicationProvider.getApplicationContext<Context>().packageName

internal const val SETTINGS = "Настройки"

internal const val SETTINGS_PACKAGE = "com.android.settings"

internal const val TIMEOUT = 5000L