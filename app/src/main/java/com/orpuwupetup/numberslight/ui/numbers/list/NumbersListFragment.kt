package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.ui.AbstractDataLoadingFragment
import javax.inject.Inject

class NumbersListFragment: AbstractDataLoadingFragment(), NumbersListFragmentContract.View {

    @Inject
    override lateinit var presenter: NumbersListFragmentContract.Presenter

    override fun getFragmentLayout(): Int = R.layout.fragment_numbers_list

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}