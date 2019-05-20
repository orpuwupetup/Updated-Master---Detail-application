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
    lateinit var detailsFagment: NumberDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)

        initViews()
    }

    private fun initViews() {
        showFragment(detailsFagment, R.id.container_details_fragment)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun notifyChildFragmentAboutNumberToDisplay(numberName: String) {
        detailsFagment.loadNumberDetails(numberName)
    }
}