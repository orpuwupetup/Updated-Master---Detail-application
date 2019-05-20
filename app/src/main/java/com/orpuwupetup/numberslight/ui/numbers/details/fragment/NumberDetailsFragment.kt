package com.orpuwupetup.numberslight.ui.numbers.details.fragment

import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.ui.AbstractFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_number_details.*
import javax.inject.Inject

class NumberDetailsFragment: AbstractFragment(),
    NumberDetailsFragmentContract.View {

    @Inject
    override lateinit var presenter: NumberDetailsFragmentContract.Presenter

    @Inject
    lateinit var picasso: Picasso

    override fun getFragmentLayout(): Int = R.layout.fragment_number_details

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    fun loadNumberDetails(numberName: String) {
        presenter.fetchNumberDetails(numberName)
    }

    override fun displayNumberDetails(numberDetails: NumberDetails) {
        text_number_name.text = numberDetails.text

        picasso.load(numberDetails.imageUrl).into(image_number_photo)
    }
}