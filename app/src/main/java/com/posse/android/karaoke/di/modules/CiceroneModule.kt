package com.posse.android.karaoke.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun navigationHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun router(): Router = cicerone.router
}