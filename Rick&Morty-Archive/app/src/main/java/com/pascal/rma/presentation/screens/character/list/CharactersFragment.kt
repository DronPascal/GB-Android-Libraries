package com.pascal.rma.presentation.screens.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pascal.rma.App
import com.pascal.rma.databinding.FragmentCharactersBinding
import com.pascal.rma.presentation.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharactersFragment : MvpAppCompatFragment(), CharactersView, BackButtonListener {

    private var vb: FragmentCharactersBinding? = null

    private val presenter by moxyPresenter {
        CharactersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCharactersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

}