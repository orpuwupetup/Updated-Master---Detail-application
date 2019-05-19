package com.orpuwupetup.numberslight.ui.numbers.list

import javax.inject.Inject

class NumbersListFragmentPresenter @Inject constructor(): NumbersListFragmentContract.Presenter {

    override var view: NumbersListFragmentContract.View? = null

    override fun takeView(view: NumbersListFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }
}