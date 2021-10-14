package com.pascal.rma.presentation.screens.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import com.google.android.material.snackbar.Snackbar
import com.pascal.rma.App
import com.pascal.rma.R
import com.pascal.rma.databinding.FragmentCategoriesBinding
import com.pascal.rma.presentation.activity.MainActivity
import com.pascal.rma.presentation.navigation.BackButtonListener
import com.pascal.rma.util.ColorUtil.getThemeColor
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
        (activity as MainActivity).setDisplayHomeAsUpEnabled(false)
        vb?.run {
            cdCharacters.setOnClickListener { presenter.onNavigateToCharacters() }
            cdLocations.setOnClickListener { presenter.onNavigateToLocations() }
            cdEpisodes.setOnClickListener { presenter.onNavigateToEpisodes() }
        }
    }

    override fun showSnackbar(message: String) {
        vb?.run {
            @ColorInt val backgroundColor = requireContext().getThemeColor(R.attr.colorSecondary)
            @ColorInt val textColor = requireContext().getThemeColor(R.attr.colorOnSecondary)
            Snackbar.make(root, message, Snackbar.LENGTH_SHORT)
                .setAction("CLOSE") { }
                .setBackgroundTint(backgroundColor)
                .setTextColor(textColor)
                .show()
        }
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

}

