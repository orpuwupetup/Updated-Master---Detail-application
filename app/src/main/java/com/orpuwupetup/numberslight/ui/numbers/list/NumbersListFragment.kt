package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.ui.AbstractDataLoadingFragment
import com.orpuwupetup.numberslight.ui.numbers.details.NumberDetailsFragmentContract
import javax.inject.Inject

class NumbersListFragment: AbstractDataLoadingFragment(), NumberDetailsFragmentContract.View {

    @Inject
    override lateinit var presenter: NumberDetailsFragmentContract.Presenter

    override fun getFragmentLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}