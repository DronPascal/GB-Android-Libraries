package com.pascal.rma.presentation.screens.character.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pascal.rma.App
import com.pascal.rma.R
import com.pascal.rma.databinding.FragmentCharactersBinding
import com.pascal.rma.domain.model.Character
import com.pascal.rma.presentation.activity.MainActivity
import com.pascal.rma.presentation.navigation.BackButtonListener
import com.pascal.rma.presentation.screens.character.list.adapter.CharacterAdapter
import com.pascal.rma.util.ConfigurationUtil.isPortrait
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
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
        activity?.title = getString(R.string.characters)
        (activity as MainActivity).setDisplayHomeAsUpEnabled(false)
        initRecyclerView()
    }

    @ExperimentalCoroutinesApi
    private fun initRecyclerView() {
        mBinding?.rvCharacters?.layoutManager = when (requireContext().isPortrait()) {
            true -> LinearLayoutManager(requireContext())
            false -> GridLayoutManager(requireContext(), 2)
        }

        mAdapter.setItemClickListener(object : CharacterAdapter.OnItemClickListener {
            override fun onItemClicked(item: Character) {
                presenter.onNavigateToCharacterDetail(character = item)
            }
        })

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

        mBinding?.swipeRefresh?.setOnRefreshListener {
            mAdapter.retry()
            mBinding?.swipeRefresh?.isRefreshing = false
        }

        mDisposable.add(presenter.getCharactersFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
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