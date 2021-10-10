package com.posse.android.karaoke.di.modules

import com.posse.android.karaoke.di.scopes.SongsScope
import com.posse.android.karaoke.model.AllSongsRepo
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.remote.IApiHolder
import com.posse.android.karaoke.utils.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class SongsModule {

    @Provides
    @SongsScope
    fun songsRepo(
        networkStatus: NetworkStatus,
        apiHolder: IApiHolder,
        allSongsRepo: AllSongsRepo
    ): SongsRepo {
        return SongsRepo(networkStatus, apiHolder, allSongsRepo)
    }
}