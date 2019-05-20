package com.orpuwupetup.numberslight.ui.numbers.details

import android.os.Bundle
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.ui.AbstractFragmentContainingActivity
import com.orpuwupetup.numberslight.ui.numbers.details.fragment.NumberDetailsFragment
import javax.inject.Inject

// container activity, made for easier management of DetailsFragment in portrait mode and on non tablet devices
class NumberDetailActivity: AbstractFragmentContainingActivity(), NumberDetailActivityContract.View {

    @Inject
    override lateinit var presenter: NumberDetailActivityContract.Presenter

    @Inject
    lateinit var detailsFragment: NumberDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
        presenter.takeView(this)
    }

    private fun initViews() {
        showFragment(detailsFragment, R.id.container_details_fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun notifyChildFragmentAboutNumberToDisplay(numberName: String) {
        if (!detailsFragment.isAdded)
            detailsFragment.setNameOfNumberDetailsToShow(numberName)
        else
            detailsFragment.showNewNumberDetails(numberName)
    }

    /*
     close activity if user is using tablet and he tries to switch to landscape, to show him main activity with
     master-detail flow
    */
    override fun closeActivity() {
        finish()
    }
}