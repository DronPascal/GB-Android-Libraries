package com.example.gb_libs_lesson6.presentation.screens.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson6.App
import com.example.gb_libs_lesson6.databinding.FragmentUserReposBinding
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.presentation.navigation.BackButtonListener
import com.example.gb_libs_lesson6.presentation.screens.repos.adapter.UserRepoRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserReposFragment : MvpAppCompatFragment(), UserReposView, BackButtonListener {

    private var vb: FragmentUserReposBinding? = null

    private val presenter by moxyPresenter {
        UserReposPresenter().apply {
            App.instance.appComponent.inject(this)
        }
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
        presenter.user = requireArguments().getParcelable(USER_ARG)
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
        fun newInstance(user: GithubUser): UserReposFragment {
            return UserReposFragment().apply {
                arguments = bundleOf(USER_ARG to user)
            }
        }

        private const val USER_ARG = "USER_KEY"
    }
}