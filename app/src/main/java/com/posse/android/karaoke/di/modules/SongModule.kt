package com.posse.android.karaoke.di.modules

import com.posse.android.karaoke.di.scopes.SongScope
import com.posse.android.karaoke.model.AllSongsRepo
import com.posse.android.karaoke.model.SongRepo
import com.posse.android.karaoke.remote.IApiHolder
import com.posse.android.karaoke.utils.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class SongModule {

    @Provides
    @SongScope
    fun songRepo(
        networkStatus: NetworkStatus,
        apiHolder: IApiHolder,
        allSongsRepo: AllSongsRepo
    ): SongRepo = SongRepo(networkStatus, apiHolder, allSongsRepo)
}