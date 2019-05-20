package com.orpuwupetup.numberslight.ui.numbers.details.fragment

import android.os.Bundle
import android.view.View
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.ui.AbstractFragment
import com.orpuwupetup.numberslight.ui.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_number_details.*
import javax.inject.Inject

class NumberDetailsFragment : AbstractFragment(),
    NumberDetailsFragmentContract.View {

    @Inject
    override lateinit var presenter: NumberDetailsFragmentContract.Presenter

    @Inject
    lateinit var picasso: Picasso

    private var displayedNumberName: String? = null

    override fun getFragmentLayout(): Int = R.layout.fragment_number_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.takeView(this)

        /*
         if displayed number name is not null it means that parent activity wanted to set this variable
         I had to do it this way, because otherwise if I called fetchNumberDetails() from setNameOfNumberDetailsToShow,
         sometimes lateinit properties (presenter) haven't got enough time to be initialized
        */
        if (displayedNumberName != null) {
            presenter.fetchNumberDetails(displayedNumberName ?: MainActivity.NO_ITEM_DETAILS_DISPLAYED)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    fun setNameOfNumberDetailsToShow(numberName: String) {
        displayedNumberName = numberName
    }

    fun showNewNumberDetails(numberName: String) {
        presenter.fetchNumberDetails(numberName)
    }

    override fun displayNumberDetails(numberDetails: NumberDetails) {
        text_error?.visibility = View.GONE

        text_number_name?.text = numberDetails.text

        image_number_photo?.let { imageView ->
            picasso.load(numberDetails.imageUrl).into(imageView)
        }
    }

    override fun showFetchingDataError() {
        text_error?.apply {
            visibility = View.VISIBLE
            text = getString(R.string.data_fetching_error)
        }
    }

    override fun showNoInternetConnectionWarning() {
        text_error?.apply {
            visibility = View.VISIBLE
            text = getString(R.string.internet_connection_error)
        }
    }
}