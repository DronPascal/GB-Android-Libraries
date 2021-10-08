package com.pascal.rma.presentation.image

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by dronpascal on 05.10.2021.
 */
class GlideImageLoader : IImageLoader<ImageView> {

    override fun loadTo(url: String, target: ImageView) {
        Glide.with(target.context)
            .load(url)
            .into(target)
    }
}