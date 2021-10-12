package com.pascal.rma.presentation.screens.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pascal.rma.App
import com.pascal.rma.R
import com.pascal.rma.databinding.FragmentCategoriesBinding
import com.pascal.rma.presentation.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by dronpascal on 05.10.2021.
 */
class CategoriesFragment : MvpAppCompatFragment(), CategoriesView, BackButtonListener {

    private var vb: FragmentCategoriesBinding? = null

    private val presenter by moxyPresenter {
        CategoriesPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCategoriesBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun initView() {
        activity?.title = getString(R.string.app_name)
        vb?.cdCharacters?.setOnClickListener { presenter.onNavigateToCharacters() }
        vb?.cdLocations?.setOnClickListener { presenter.onNavigateToLocations() }
        vb?.cdEpisodes?.setOnClickListener { presenter.onNavigateToEpisodes() }
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

}