package com.orpuwupetup.numberslight.data.source.repository

import android.annotation.SuppressLint
import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.source.NumbersDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumbersRepository @Inject constructor(
    private val numbersDataSource: NumbersDataSource
) : NumbersDataSource {

    private var cacheIsDirty = false

    // cache, to limit numbers of http calls and to provide data faster
    private val numbersCache: MutableList<Number> = mutableListOf()

    override fun getNumbersJSON(): Single<List<Number>> {

        if (!cacheIsDirty && numbersCache.isNotEmpty()) {
            return Single.just(numbersCache as List<Number>)
        }

        return getAndSaveNumbersFromRemoteSource()
    }

    /*
    this disposable will dispose itself at completion, and because repository is provided as a singleton, I don't
    have to dispose it to detach reference to any particular view, because it doesn't belong to any, and I actually
    want it to complete, even if class from which it was called dies, to not interrupt fetching data, for example during
    device orientation change (that is why I implemented it via callback pattern ass well, because if I would subscribe
    to it via getNumbersSingle method inside a presenter, call would get cancelled after orientation change because of
    java.io.IOException, and data won't be fetched and cached for future usage)
    */
    @SuppressLint("CheckResult")
    fun getNumbers(callback: NumbersFetchedCallback) {
        getNumbersJSON()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ fetchedNumbers ->
                if (fetchedNumbers.isNotEmpty())
                    callback.onNumbersFetched(fetchedNumbers)
                else
                    callback.onError()
            }, {
                callback.onError()
            })
    }

    private fun getAndSaveNumbersFromRemoteSource(): Single<List<Number>> {
        return numbersDataSource.getNumbersJSON()
            .doOnSuccess { numbersFromRemoteSource ->
                cacheIsDirty = false
                numbersCache.clear()
                numbersCache.addAll(numbersFromRemoteSource)
            }
    }

    fun refresh() {
        cacheIsDirty = true
    }

    interface NumbersFetchedCallback {
        fun onNumbersFetched(numbers: List<Number>)
        fun onError()
    }
}