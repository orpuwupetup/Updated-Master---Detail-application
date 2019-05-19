package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.ui.BasePresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface NumbersListFragmentContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}