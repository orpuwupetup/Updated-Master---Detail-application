package com.orpuwupetup.numberslight.ui.numbers.list

class NumbersListFragmentState(private val scroll: Int?, private val selectedItemPosition: Int?)
    : NumbersListFragmentContract.State {

    override fun getScroll(): Int? = scroll

    override fun getSelectedItemPosition(): Int? = selectedItemPosition
}