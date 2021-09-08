package com.pascal.gbandroidlibrariesles1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pascal.gbandroidlibrariesles1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this, CountersModel())

    private var _vb: ActivityMainBinding? = null

    private val vb
        get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        initView()
    }

    private fun initView() {
        val listener = View.OnClickListener {
            presenter.counterClick(
                when (it.id) {
                    R.id.btn_counter1 -> 0
                    R.id.btn_counter2 -> 1
                    R.id.btn_counter3 -> 2
                    else -> throw IllegalStateException("Такого индекса нет")
                }
            )
        }

        vb.btnCounter1.setOnClickListener(listener)
        vb.btnCounter2.setOnClickListener(listener)
        vb.btnCounter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> vb.btnCounter1.text = text
            1 -> vb.btnCounter2.text = text
            2 -> vb.btnCounter3.text = text
        }
    }
}