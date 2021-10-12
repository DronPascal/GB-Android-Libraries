package com.pascal.rma.di.module

import android.content.Context
import android.widget.ImageView
import com.pascal.rma.App
import com.pascal.rma.presentation.image.GlideImageLoader
import com.pascal.rma.presentation.image.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 12.10.2021.
 */
@Module
object GlideModule {

    @Singleton
    @Provides
    fun provideGlideImageLoader(context: Context): IImageLoader<ImageView> {
        return GlideImageLoader<ImageView>(context)
    }

}