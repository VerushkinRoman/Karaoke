package com.posse.android.karaoke.di.modules

import android.widget.ImageView
import com.posse.android.karaoke.images.ImageLoader
import com.posse.android.karaoke.images.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface ImageModule {

    @Binds
    fun imageLoader(impl: ImageLoaderImpl): ImageLoader<ImageView>

    companion object{
        @Provides
        @Singleton
        fun image() = ImageLoaderImpl()
    }
}