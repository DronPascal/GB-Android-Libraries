package com.example.gb_libs_lesson5.ui.screens.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson5.App
import com.example.gb_libs_lesson5.data.GithubUserRepoRepository
import com.example.gb_libs_lesson5.databinding.FragmentUserReposBinding
import com.example.gb_libs_lesson5.navigation.BackButtonListener
import com.example.gb_libs_lesson5.ui.screens.repos.adapter.UserRepoRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserReposFragment : MvpAppCompatFragment(), UserReposView, BackButtonListener {

    private var vb: FragmentUserReposBinding? = null

    private val presenter by moxyPresenter {
        UserReposPresenter(
            GithubUserRepoRepository(),
            App.instance.router
        )
    }

    private val adapter by lazy {
        UserRepoRVAdapter(
            presenter.userRepoListPresenter,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserReposBinding.inflate(inflater, container, false)
            .also { vb = it }.root
    }

    override fun init() {
        presenter.reposUrl = requireArguments().getString(URL_ARG)
        vb?.rvRepos?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvRepos?.adapter = adapter
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
        fun newInstance(userReposUrl: String): UserReposFragment {
            return UserReposFragment().apply {
                arguments = bundleOf(URL_ARG to userReposUrl)
            }
        }

        private const val URL_ARG = "KEY_ARG"
    }
}