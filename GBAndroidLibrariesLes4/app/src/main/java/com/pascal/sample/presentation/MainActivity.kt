package com.pascal.sample.presentation

import android.os.Bundle
import android.os.PersistableBundle
import com.pascal.sample.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity

/**
 * Created by dronpascal on 20.09.2021.
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    private var _vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_vb?.root)
        initView()
    }

    private fun initView() {
        _vb?.btnConvert?.setOnClickListener {

        }
    }
}