package com.example.gb_libs_lesson5.ui.screens.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs_lesson5.databinding.ItemUserRepoBinding
import com.example.gb_libs_lesson5.ui.items.IUserRepoListPresenter

class UserRepoRVAdapter(
    private val presenter: IUserRepoListPresenter,
) : RecyclerView.Adapter<UserRepoRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ItemUserRepoBinding) : RecyclerView.ViewHolder(vb.root),
        UserRepoItemView {

        override var pos: Int = -1

        override fun showRepoName(name: String) {
            vb.tvName.text = name
        }
    }
}