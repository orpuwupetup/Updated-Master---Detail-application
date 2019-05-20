package com.orpuwupetup.numberslight.ui.main

import android.util.Log
import com.orpuwupetup.numberslight.utils.devicestate.DeviceStateChecker
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val deviceStateChecker: DeviceStateChecker): MainActivityContract.Presenter {

    override var view: MainActivityContract.View? = null

    override fun takeView(view: MainActivityContract.View) {
        this.view = view

        Log.e("main presenter", "landscape: ${deviceStateChecker.isLandscape()}, \n tablet: ${deviceStateChecker.isTablet()}")
    }

    override fun dropView() {
        this.view = null
    }

    override fun listNumberClicked(clickedNumberName: String) {
        view?.showNumberDetails(clickedNumberName)
    }
}