package com.orpuwupetup.numberslight.ui.numbers.details

import com.orpuwupetup.numberslight.ui.BasePresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface NumberDetailsFragmentContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}