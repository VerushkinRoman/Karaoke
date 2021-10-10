package com.posse.android.karaoke.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.posse.android.karaoke.remote.ApiHolder
import com.posse.android.karaoke.remote.IApiHolder
import com.posse.android.karaoke.remote.LastFMService
import com.posse.android.karaoke.utils.AndroidNetworkStatus
import com.posse.android.karaoke.utils.NetworkStatus
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
interface ApiModule {

    @Binds
    fun apiHolder(impl: ApiHolder): IApiHolder

    companion object {

        @Provides
        @Singleton
        @Named("baseUrl")
        fun baseUrl(): String = "http://ws.audioscrobbler.com/2.0/"

        @Provides
        @Singleton
        fun gson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        @Singleton
        @Provides
        fun lastFMService(
            @Named("baseUrl") baseUrl: String,
            gson: Gson
        ): LastFMService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(LastFMService::class.java)
        }

        @Provides
        @Singleton
        fun networkStatus(context: Context): NetworkStatus =
            AndroidNetworkStatus(context)
    }
}
