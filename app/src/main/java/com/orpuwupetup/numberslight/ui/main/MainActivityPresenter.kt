package com.orpuwupetup.numberslight.ui.main

import android.util.Log
import com.orpuwupetup.numberslight.data.source.repository.NumbersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val numbersRepository: NumbersRepository,
    private val compositeDisposable: CompositeDisposable
): MainActivityContract.Presenter {

    override var view: MainActivityContract.View? = null

    override fun takeView(view: MainActivityContract.View) {
        this.view = view

        // TODO check if cache is dirty via cache manager
        numbersRepository.getNumbersJSON()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("main activity presenter", "fetched numbers quantity: ${it.size}")

            }, {
                it.printStackTrace()
            }).apply {
                compositeDisposable.add(this)
            }
    }

    override fun dropView() {
        this.view = null
        compositeDisposable.dispose()
    }
}