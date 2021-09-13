package com.example.gb_libs_lesson1.view.ui.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.databinding.FragmentUserDetailBinding
import com.example.gb_libs_lesson1.model.GithubUser
import com.example.gb_libs_lesson1.presentation.UserDetailPresenter
import com.example.gb_libs_lesson1.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {

    companion object {
        fun newInstance(gitHubUser: GithubUser): UserDetailFragment {
            return UserDetailFragment().apply {
                arguments = bundleOf(USER_ARGUMENT to gitHubUser)
            }
        }

        private const val USER_ARGUMENT = "USER_ARG_KEY"
    }

    private var vb: FragmentUserDetailBinding? = null

    private val presenter by moxyPresenter {
        UserDetailPresenter(App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserDetailBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.user = requireArguments().getParcelable<GithubUser>(USER_ARGUMENT)

        initView()
    }

    private fun initView() {
        vb?.login?.text = presenter.user?.login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}