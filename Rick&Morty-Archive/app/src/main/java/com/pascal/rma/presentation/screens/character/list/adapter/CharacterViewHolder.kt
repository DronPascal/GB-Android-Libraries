package com.pascal.rma.presentation.screens.character.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pascal.rma.App
import com.pascal.rma.R
import com.pascal.rma.databinding.ItemCharacterBinding
import com.pascal.rma.domain.model.Character
import com.pascal.rma.presentation.image.IImageLoader
import javax.inject.Inject

/**
 * Created by dronpascal on 12.10.2021.
 */
class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
) : RecyclerView.ViewHolder(binding.root) {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    init {
        App.instance.appComponent.inject(this)
    }

    fun bind(character: Character) {
        with(character) {
            imageLoader.loadTo(image, binding.imgAvatar)
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_character, parent, false)

            val binding = ItemCharacterBinding.bind(view)

            return CharacterViewHolder(
                binding
            )
        }
    }
}