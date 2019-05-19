package com.orpuwupetup.numberslight.ui.numbers.details

import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.ui.AbstractDataLoadingFragment
import javax.inject.Inject

class NumberDetailsFragment: AbstractDataLoadingFragment(), NumberDetailsFragmentContract.View {

    @Inject
    override lateinit var presenter: NumberDetailsFragmentContract.Presenter

    override fun getFragmentLayout(): Int = R.layout.fragment_number_details

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}