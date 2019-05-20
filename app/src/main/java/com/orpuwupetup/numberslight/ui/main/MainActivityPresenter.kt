package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.utils.devicestate.DeviceStateChecker
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val deviceStateChecker: DeviceStateChecker) :
    MainActivityContract.Presenter {

    override var view: MainActivityContract.View? = null

    /*
     I decided to keep view state in its presenter, their lifecycles are tied closely together and I thought that making
     another repository for state alone is too cumbersome, but I could be wrong here
    */
    private var displayedItemName: String? = null

    override fun takeView(view: MainActivityContract.View) {
        takeView(view, null)
    }

    override fun takeView(view: MainActivityContract.View, state: MainActivityContract.State?) {
        this.view = view



        displayedItemName = state?.getDisplayedItemName()

        if (!deviceStateChecker.isTablet())
            view.setPhoneLayout()
        else {
            if (deviceStateChecker.isLandscape()) {
                view.showMasterDetailLayout(
                    displayedItemName ?: MainActivity.NO_ITEM_DETAILS_DISPLAYED
                )
            } else {
                view.setTabletPortraitLayout()
            }
        }
    }

    override fun getState(): MainActivityContract.State =
        MainActivityState(displayedItemName)

    override fun dropView() {
        this.view = null

        /*
         delete view state, view is no longer associated with this presenter, so presenter shouldn't keep track of
         its state
        */

        displayedItemName = null
    }

    override fun listNumberClicked(clickedNumberName: String) {

        if (!deviceStateChecker.isTablet() || deviceStateChecker.isTabletInPortraitMode())
            view?.showNumberDetailsForPhoneAndPortraitTablet(clickedNumberName)
        else
            view?.showMasterDetailLayoutDetails(clickedNumberName)

        displayedItemName = clickedNumberName
    }
}