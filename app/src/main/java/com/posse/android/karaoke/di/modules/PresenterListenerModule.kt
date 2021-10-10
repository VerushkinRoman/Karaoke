package com.posse.android.karaoke.di.modules

import com.posse.android.karaoke.navigation.PresenterDestroyListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterListenerModule {

    @Provides
    fun listener(): PresenterDestroyListener = PresenterDestroyListener()
}