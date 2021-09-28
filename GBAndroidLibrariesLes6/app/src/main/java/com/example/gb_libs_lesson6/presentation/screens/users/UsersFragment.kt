package com.example.gb_libs_lesson6.presentation.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson6.App
import com.example.gb_libs_lesson6.databinding.FragmentUsersBinding
import com.example.gb_libs_lesson6.domain.interactors.GithubInteractors
import com.example.gb_libs_lesson6.navigation.BackButtonListener
import com.example.gb_libs_lesson6.presentation.images.GlideImageLoader
import com.example.gb_libs_lesson6.presentation.screens.users.adapter.UsersRVAdapter
import com.example.gb_libs_lesson6.utils.AndroidNetworkStatus
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter {
        UsersPresenter(
            GithubInteractors.build(AndroidNetworkStatus(requireContext())),
            App.instance.router
        )
    }

    private val adapter by lazy {
        UsersRVAdapter(
            presenter.usersListPresenter,
            GlideImageLoader()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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