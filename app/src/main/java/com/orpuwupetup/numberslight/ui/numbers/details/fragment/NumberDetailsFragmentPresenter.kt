package com.orpuwupetup.numberslight.ui.numbers.details.fragment

import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.data.source.repository.NumbersDetailRepository
import com.orpuwupetup.numberslight.utils.network.NetworkConnectionChecker
import javax.inject.Inject

class NumberDetailsFragmentPresenter @Inject constructor(
    private val numbersDetailRepository: NumbersDetailRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) :
    NumberDetailsFragmentContract.Presenter {

    override var view: NumberDetailsFragmentContract.View? = null

    override fun takeView(view: NumberDetailsFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun fetchNumberDetails(numberName: String) {

        if (networkConnectionChecker.isNetworkConnected()) {
            /*
            Here I should check if numbers cache has expired or not (for example save caching time every time it is done, and
            than compare it here) and if it has, call numbersRepository.refresh(), but it is not needed for now, because I
            know that data from the link is quite static, and besides, cache will refresh itself every time the app is
            killed by user or by Android itself
             */
            numbersDetailRepository.getNumberDetails(
                numberName, object : NumbersDetailRepository.NumberDetailsFetchedCallback {
                    override fun onNumberDetailsFetched(numberDetails: NumberDetails) {
                        view?.displayNumberDetails(numberDetails)
                    }

                    override fun onError(throwable: Throwable?) {
                        view?.showFetchingDataError()
                    }
                }
            )
        } else {
            // refreshing repository to fetch new data from remote after internet reconnection
            numbersDetailRepository.refresh()
            view?.showNoInternetConnectionWarning()
        }
    }
}