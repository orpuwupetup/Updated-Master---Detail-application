package com.orpuwupetup.numberslight.ui.main

import android.util.Log
import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.source.repository.NumbersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val numbersRepository: NumbersRepository
): MainActivityContract.Presenter {

    override var view: MainActivityContract.View? = null

    override fun takeView(view: MainActivityContract.View) {
        this.view = view

        // TODO check if cache is dirty via cache manager
        numbersRepository.getNumbers(object: NumbersRepository.NumbersFetchedCallback {
            override fun onNumbersFetched(numbers: List<Number>) {
                Log.e("presenter", "number of items: ${numbers.size}")
            }

            override fun onError() {
                Log.e("presenter", "problem fetching data")
            }
        })
    }

    override fun dropView() {
        this.view = null
    }
}