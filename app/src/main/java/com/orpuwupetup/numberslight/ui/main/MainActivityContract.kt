package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.ui.BaseState
import com.orpuwupetup.numberslight.ui.BaseStatefulPresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface MainActivityContract {

    interface View: BaseView<Presenter> {
        fun showNumberDetailsForPhoneAndPortraitTablet(clickedNumberName: String)
        fun showMasterDetailLayout(numberName: String, selectedNumberPosition: Int)
        fun showMasterDetailLayoutDetails(numberName: String)

        fun setPhoneLayout()
        fun setTabletPortraitLayout(selectedNumberPosition: Int)
    }

    interface Presenter: BaseStatefulPresenter<View, State> {
        fun listNumberClicked(clickedNumberName: String, clickedNumberIndex: Int)
    }

    interface State: BaseState {
        fun getListScrollPosition(): Int?
        fun getSelectedItemPosition(): Int?
        fun getDisplayedItemName(): String?
    }
}