package com.pascal.sample.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import com.pascal.sample.App
import com.pascal.sample.R
import com.pascal.sample.databinding.ActivityMainBinding
import com.pascal.sample.domain.FileName
import com.pascal.sample.domain.interactors.StorageInteractors
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

/**
 * Created by dronpascal on 20.09.2021.
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter {
        MainPresenter(
            StorageInteractors
                .build(App.instance.internalStorageImpl)
        )
    }

    private var _vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_vb?.root)
        initView()
    }
    
    private fun initView() {
        findViewById<Button>(R.id.btn_convert).setOnClickListener {
            presenter.onConvertAndSaveFile(FileName.flowerFile)
        }
    }

    override fun showToast(msg: String?) {
        msg?.let {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}