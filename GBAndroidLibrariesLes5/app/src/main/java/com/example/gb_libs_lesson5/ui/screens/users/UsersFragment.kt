package com.example.gb_libs_lesson5.ui.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson5.App
import com.example.gb_libs_lesson5.data.GithubUsersRepo
import com.example.gb_libs_lesson5.databinding.FragmentUsersBinding
import com.example.gb_libs_lesson5.navigation.BackButtonListener
import com.example.gb_libs_lesson5.ui.images.GlideImageLoader
import com.example.gb_libs_lesson5.ui.screens.users.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router) }

    private val adapter by lazy {
        UsersRVAdapter(
            presenter.usersListPresenter,
            GlideImageLoader()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {

        private const val KEY_ARG = "KEY_ARG"
    }
}