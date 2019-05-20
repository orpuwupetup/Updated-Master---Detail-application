package com.orpuwupetup.numberslight.ui.numbers.details.fragment

import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.data.source.repository.NumbersDetailRepository
import javax.inject.Inject

class NumberDetailsFragmentPresenter @Inject constructor(private val numbersDetailRepository: NumbersDetailRepository):
    NumberDetailsFragmentContract.Presenter {

    override var view: NumberDetailsFragmentContract.View? = null

    override fun takeView(view: NumberDetailsFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun fetchNumberDetails(numberName: String) {
        numbersDetailRepository.getNumberDetails(
            numberName, object: NumbersDetailRepository.NumberDetailsFetchedCallback {
                override fun onNumberDetailsFetched(numberDetails: NumberDetails) {
                    view?.displayNumberDetails(numberDetails)
                }

                override fun onError(throwable: Throwable?) {
                    // show error
                }
            }
        )
    }
}