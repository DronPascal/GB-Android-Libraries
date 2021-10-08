package com.pascal.rma.presentation.screens.base

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