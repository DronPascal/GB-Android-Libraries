package com.pascal.rma.presentation.screens.character.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import com.pascal.rma.App
import com.pascal.rma.R
import com.pascal.rma.databinding.FragmentCharactersBinding
import com.pascal.rma.presentation.navigation.BackButtonListener
import com.pascal.rma.presentation.screens.character.list.adapter.CharacterAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharactersFragment : MvpAppCompatFragment(), CharactersView, BackButtonListener {

    private var mBinding: FragmentCharactersBinding? = null
    private val mAdapter: CharacterAdapter by lazy { CharacterAdapter() }
    private val mDisposable = CompositeDisposable()
    private var mRetryDialog: AlertDialog? = null

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
            mBinding = it
        }.root
    }

    @ExperimentalCoroutinesApi
    override fun initView() {
        initRecyclerView()
    }

    @ExperimentalCoroutinesApi
    private fun initRecyclerView() {
        mBinding?.rvCharacters?.adapter = mAdapter

        mAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                presenter.onPagingError(it.error)
            }
        }

        mDisposable.add(presenter.getCharacters().subscribe {
            mAdapter.submitData(lifecycle, it)
        })
    }

    override fun showRetryAlertDialog(message: String?) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_paging)
            .setMessage(message ?: getString(R.string.unknown_error))
            .setPositiveButton(R.string.retry) { _, _ ->
                mAdapter.retry()
                presenter.onRetryDialogConfirm()
            }
            .setNegativeButton(R.string.cancel) { _, _ -> presenter.onRetryDialogCancel() }
            .setOnCancelListener { presenter.onRetryDialogCancel() }
            .show()
    }

    override fun hideRetryAlertDialog() {
        mRetryDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogDismiss(mRetryDialog)
        mDisposable.dispose()
        mBinding = null
    }

    private fun dialogDismiss(dialog: AlertDialog?) {
        dialog?.let {
            if (it.isShowing) {
                it.setOnCancelListener(null)
                it.dismiss()
            }
        }
    }

}