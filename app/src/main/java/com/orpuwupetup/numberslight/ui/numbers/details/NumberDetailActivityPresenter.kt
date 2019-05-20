package com.orpuwupetup.numberslight.ui.numbers.details

import com.orpuwupetup.numberslight.utils.devicestate.DeviceStateChecker
import javax.inject.Inject

class NumberDetailActivityPresenter @Inject constructor(private val numberToDisplayName: String?,
                                                        private val deviceStateChecker: DeviceStateChecker) :
    NumberDetailActivityContract.Presenter {

    override var view: NumberDetailActivityContract.View? = null

    override fun takeView(view: NumberDetailActivityContract.View) {
        this.view = view

        if (!numberToDisplayName.isNullOrBlank())
            view.notifyChildFragmentAboutNumberToDisplay(numberToDisplayName)

        if (deviceStateChecker.isTablet() && deviceStateChecker.isLandscape())
            view.closeActivity()
    }

    override fun dropView() {
        this.view = null
    }
}