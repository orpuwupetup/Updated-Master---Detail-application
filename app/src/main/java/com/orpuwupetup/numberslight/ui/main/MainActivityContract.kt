package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.ui.BaseState
import com.orpuwupetup.numberslight.ui.BaseStatefulPresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface MainActivityContract {

    interface View: BaseView<Presenter> {
        fun showNumberDetailsForPhoneAndPortraitTablet(clickedNumberName: String)
        fun showMasterDetailLayout(currentNumberDetailsName: String)
        fun showMasterDetailLayoutDetails(numberName: String)

        fun setPhoneLayout()
        fun setTabletPortraitLayout()
    }

    interface Presenter: BaseStatefulPresenter<View, State> {
        fun listNumberClicked(clickedNumberName: String)
    }

    interface State: BaseState {
        fun getDisplayedItemName(): String?
    }
}