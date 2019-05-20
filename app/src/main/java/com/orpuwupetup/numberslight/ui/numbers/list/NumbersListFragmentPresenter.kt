package com.orpuwupetup.numberslight.ui.numbers.list

import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.source.repository.NumbersRepository
import com.orpuwupetup.numberslight.utils.network.NetworkConnectionChecker
import javax.inject.Inject

class NumbersListFragmentPresenter @Inject constructor(
    private val numbersRepository: NumbersRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
): NumbersListFragmentContract.Presenter {

    override var view: NumbersListFragmentContract.View? = null

    /*
     I decided to keep view state in its presenter, their lifecycles are tied closely together and I thought that making
     another repository for state alone is too cumbersome, but I could be wrong here
    */
    private var selectedItemPosition: Int? = null
    private var numbersListScrollPosition: Int? = null

    override fun takeView(view: NumbersListFragmentContract.View) =
        takeView(view, null)

    override fun takeView(view: NumbersListFragmentContract.View, state: NumbersListFragmentContract.State?) {
        this.view = view
        fetchNumbersList()

        // if there is scroll saved in state, notify view to set it correctly
        numbersListScrollPosition = state?.getScroll()
        numbersListScrollPosition?.let { listScroll ->
            view.setScrollPosition(listScroll)
        }

        // if there is selected item position saved, notify view to select it again
        selectedItemPosition = state?.getSelectedItemPosition()
        selectedItemPosition?.let{ selectedItemPosition ->
            view.setSelectedItem(selectedItemPosition)
        }
    }

    override fun getState(): NumbersListFragmentContract.State =
        NumbersListFragmentState(numbersListScrollPosition, selectedItemPosition)

    private fun fetchNumbersList() {
        if (networkConnectionChecker.isNetworkConnected()) {
            /*
        Here I should check if numbers cache has expired or not (for example save caching time every time it is done, and
        than compare it here) and if it has, call numbersRepository.refresh(), but it is not needed for now, because I
        know that data from the link is quite static, and besides, cache will refresh itself every time the app is
        killed by user or by Android itself
         */
            numbersRepository.getNumbers(object : NumbersRepository.NumbersFetchedCallback {
                override fun onNumbersFetched(numbers: List<Number>) {
                    view?.showNumbersList(numbers)
                }

                override fun onError(throwable: Throwable?) {
                    view?.showFetchingDataError()
                }
            })
        } else {
            // refreshing repository to fetch new data from remote after internet reconnection
            numbersRepository.refresh()
            view?.showNoInternetConnectionWarning()
        }
    }

    override fun tryToFetchList() {
        fetchNumbersList()
    }

    override fun dropView() {
        this.view = null

        /*
         delete view state, view is no longer associated with this presenter, so presenter shouldn't keep track of
         its state
        */
        selectedItemPosition = null
        numbersListScrollPosition = null
    }

    override fun onItemClicked(clickedItemPosition: Int, selectedItemName: String) {
        this.selectedItemPosition = clickedItemPosition
        view?.notifyListenersAboutItemClicked(selectedItemName)
    }

    override fun onScrollChanged(scroll: Int) {
        this.numbersListScrollPosition = scroll
    }
}