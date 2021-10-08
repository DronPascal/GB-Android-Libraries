package com.pascal.rma.presentation.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pascal.rma.databinding.FragmentCategoriesBinding
import com.pascal.rma.presentation.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by dronpascal on 05.10.2021.
 */
//class bFragment : MvpAppCompatFragment(), bView, BackButtonListener {
//
//    private var vb: Binding? = null
//
//    private val presenter by moxyPresenter {
//        bPresenter().apply {
//            App.instance.appComponent.inject(this)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return Binding.inflate(inflater, container, false).also {
//            vb = it
//        }.root
//    }
//
//    override fun backPressed(): Boolean {
//        TODO() // return presenter.onBackPressed()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        vb = null
//    }
//
//}