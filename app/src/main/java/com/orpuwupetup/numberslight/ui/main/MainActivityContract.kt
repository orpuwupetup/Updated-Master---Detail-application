package com.orpuwupetup.numberslight.ui.main

import com.orpuwupetup.numberslight.ui.BasePresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface MainActivityContract {

    interface View: BaseView<Presenter> {
        fun showNumberDetails(clickedNumberName: String)

    }

    interface Presenter: BasePresenter<View> {
        fun listNumberClicked(clickedNumberName: String)
    }
}