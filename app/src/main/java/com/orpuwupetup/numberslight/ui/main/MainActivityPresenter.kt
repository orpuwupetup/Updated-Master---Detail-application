package com.orpuwupetup.numberslight.ui.main

import android.util.Log
import com.orpuwupetup.numberslight.ui.numbers.list.adapter.NumbersAdapter
import com.orpuwupetup.numberslight.utils.devicestate.DeviceStateChecker
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val deviceStateChecker: DeviceStateChecker) :
    MainActivityContract.Presenter {

    override var view: MainActivityContract.View? = null

    private var selectedItemPosition: Int? = null
    private var listScrollPosition: Int? = null
    private var displayedItemName: String? = null

    override fun takeView(view: MainActivityContract.View) {
        takeView(view, null)
    }

    override fun takeView(view: MainActivityContract.View, state: MainActivityContract.State?) {
        this.view = view

        listScrollPosition = state?.getListScrollPosition()

        selectedItemPosition = state?.getSelectedItemPosition()

        displayedItemName = state?.getDisplayedItemName()

        if (!deviceStateChecker.isTablet())
            view.setPhoneLayout()
        else {
            if (deviceStateChecker.isLandscape()) {
                view.showMasterDetailLayout(
                    displayedItemName ?: MainActivity.NO_ITEM_DETAILS_DISPLAYED,
                    selectedItemPosition ?: NumbersAdapter.NO_POSITION_SELECTED
                )
            } else {
                view.setTabletPortraitLayout(selectedItemPosition ?: NumbersAdapter.NO_POSITION_SELECTED)
            }
        }
    }

    override fun getState(): MainActivityContract.State =
        MainActivityState(listScrollPosition, selectedItemPosition, displayedItemName)

    override fun dropView() {
        this.view = null

        /*
         delete view state, view is no longer associated with this presenter, so presenter shouldn't keep track of
         its state
        */
        selectedItemPosition = null
        listScrollPosition = null
        displayedItemName = null
    }

    override fun listNumberClicked(clickedNumberName: String, clickedNumberIndex: Int) {

        if (!deviceStateChecker.isTablet() || deviceStateChecker.isTabletInPortraitMode())
            view?.showNumberDetailsForPhoneAndPortraitTablet(clickedNumberName)
        else
            view?.showMasterDetailLayoutDetails(clickedNumberName)

        displayedItemName = clickedNumberName
        selectedItemPosition = clickedNumberIndex
    }
}