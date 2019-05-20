package com.orpuwupetup.numberslight.ui.numbers.details

import javax.inject.Inject

class NumberDetailActivityPresenter @Inject constructor(private val numberToDisplayName: String): NumberDetailActivityContract.Presenter {

    override var view: NumberDetailActivityContract.View? = null

    override fun takeView(view: NumberDetailActivityContract.View) {
        this.view = view

        view.notifyChildFragmentAboutNumberToDisplay(numberToDisplayName)
    }

    override fun dropView() {
        this.view = null
    }
}