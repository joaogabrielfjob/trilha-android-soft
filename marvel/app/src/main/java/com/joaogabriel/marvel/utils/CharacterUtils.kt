package com.joaogabriel.marvel.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class CharacterUtils {
    fun setCharacterImage(context: Context, url: String?, image: ImageView) {
        Glide.with(context).load(url).into(image)
    }
}