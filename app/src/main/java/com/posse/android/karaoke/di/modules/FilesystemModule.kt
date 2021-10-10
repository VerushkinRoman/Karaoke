package com.posse.android.karaoke.di.modules

import com.posse.android.karaoke.utils.FilesystemWorker
import com.posse.android.karaoke.utils.FilesystemWorkerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FilesystemModule {

    @Provides
    @Singleton
    fun filesystemWorker(): FilesystemWorker = FilesystemWorkerImpl()
}