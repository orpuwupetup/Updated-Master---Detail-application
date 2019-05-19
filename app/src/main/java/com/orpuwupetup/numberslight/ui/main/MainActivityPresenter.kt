package com.orpuwupetup.numberslight.ui.main

import javax.inject.Inject

class MainActivityPresenter @Inject constructor(): MainActivityContract.Presenter {

    override var view: MainActivityContract.View? = null

    override fun takeView(view: MainActivityContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }
}