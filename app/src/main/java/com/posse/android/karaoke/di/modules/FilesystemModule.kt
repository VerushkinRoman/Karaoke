package com.posse.android.karaoke.di.modules

import com.posse.android.karaoke.utils.FilesystemWorker
import com.posse.android.karaoke.utils.FilesystemWorkerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface FilesystemModule {

    @Binds
    fun filesystemWorker(impl: FilesystemWorkerImpl): FilesystemWorker

    companion object {
        @Provides
        @Singleton
        fun filesystem() = FilesystemWorkerImpl()
    }
}