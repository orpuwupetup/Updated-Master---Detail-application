package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.ui.BasePresenter
import com.orpuwupetup.numberslight.ui.BaseState
import com.orpuwupetup.numberslight.ui.BaseStatefulPresenter
import com.orpuwupetup.numberslight.ui.BaseView

interface NumbersListFragmentContract {

    interface View: BaseView<Presenter> {
        fun showNumbersList(numbers: List<Number>)
        fun setScrollPosition(scroll: Int)
        fun setSelectedItem(itemPosition: Int)
        fun notifyListenersAboutItemClicked(selectedItemName: String)
    }

    interface Presenter: BaseStatefulPresenter<View, State> {
        fun onItemClicked(clickedItemPosition: Int, selectedItemName: String)
        fun onScrollChanged(scroll: Int)
    }

    interface State: BaseState {
        fun getScroll(): Int?
        fun getSelectedItemPosition(): Int?
    }
}