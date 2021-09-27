package com.example.gb_libs_lesson5.ui.screens.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import com.example.gb_libs_lesson5.App
import com.example.gb_libs_lesson5.data.model.GithubUserRepoItem
import com.example.gb_libs_lesson5.databinding.FragmentRepoBinding
import com.example.gb_libs_lesson5.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {

    private var vb: FragmentRepoBinding? = null
    private var repo: GithubUserRepoItem? = null

    private val presenter by moxyPresenter {
        RepoPresenter(App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepoBinding.inflate(inflater, container, false)
            .also { vb = it }.root
    }

    override fun init() {
        repo = requireArguments().getParcelable<GithubUserRepoItem>(REPO_ARG)
        val text = "Forks count: ${repo?.forksCount ?: 0}"
        vb?.forks?.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {
        fun newInstance(repo: GithubUserRepoItem): RepoFragment {
            return RepoFragment().apply {
                arguments = bundleOf(REPO_ARG to repo)
            }
        }

        private const val REPO_ARG = "REPO_ARG"
    }
}