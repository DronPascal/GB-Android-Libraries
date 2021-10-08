package com.pascal.rma.presentation.item

/**
 * Created by dronpascal on 05.10.2021.
 */
interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}