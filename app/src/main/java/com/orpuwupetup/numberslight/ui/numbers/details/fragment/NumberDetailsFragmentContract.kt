package com.orpuwupetup.numberslight.ui.numbers.details.fragment

import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.ui.BasePresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface NumberDetailsFragmentContract {

    interface View: BaseView<Presenter> {
        fun displayNumberDetails(numberDetails: NumberDetails)
    }

    interface Presenter: BasePresenter<View> {
        fun fetchNumberDetails(numberName: String)
    }
}