package com.pascal.rma.presentation.screens.character.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pascal.rma.domain.model.Character

/**
 * Created by dronpascal on 12.10.2021.
 */
class CharacterAdapter : PagingDataAdapter<Character, CharacterViewHolder>(
    COMPARATOR
) {

    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        getItem(position)?.let { character ->
            itemClickListener?.let { listener ->
                holder.bind(
                    character,
                    listener::onItemClicked
                )
            }
        }
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(item: Character)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

}
