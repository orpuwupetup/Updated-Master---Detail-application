package com.orpuwupetup.numberslight.ui.numbers.details

import com.orpuwupetup.numberslight.ui.BasePresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface NumberDetailActivityContract {

    interface View: BaseView<Presenter> {
        fun notifyChildFragmentAboutNumberToDisplay(numberName: String)
    }

    interface Presenter: BasePresenter<View>
}