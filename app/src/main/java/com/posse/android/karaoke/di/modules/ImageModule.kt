package com.posse.android.karaoke.di.modules

import android.widget.ImageView
import com.posse.android.karaoke.images.ImageLoader
import com.posse.android.karaoke.images.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun imageLoader(): ImageLoader<ImageView> = ImageLoaderImpl()
}