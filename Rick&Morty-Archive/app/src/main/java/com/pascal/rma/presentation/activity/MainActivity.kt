package com.pascal.rma.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import com.pascal.rma.App
import com.pascal.rma.R
import com.pascal.rma.databinding.ActivityMainBinding
import com.pascal.rma.presentation.navigation.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    init {
        App.instance.appComponent.inject(this)
    }

    private val presenter by moxyPresenter {
        App.instance.appComponent.presenter()
    }

    private var binding: ActivityMainBinding? = null

    private val navigator = SupportAppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return presenter.onBackPressed()
    }

    fun setDisplayHomeAsUpEnabled(showHomeAsUp: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}


