package com.orpuwupetup.numberslight.data.source.repository

import android.annotation.SuppressLint
import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.data.source.NumberDetailsDataSource import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/*
 number details included in different repository than numbers list, because though they are close enough to be contained in
 single one, both should have different caches, callback, refresh methods etc
*/
@Singleton
class NumbersDetailRepository @Inject constructor(
    private val numberDetailsDataSource: NumberDetailsDataSource): NumberDetailsDataSource {

    // cache, to limit numbers of http calls and to provide data faster
    private var numberDetailsCache: NumberDetails? = null
    private var cacheIsDirty = false

    override fun fetchNumberDetails(numberName: String): Single<NumberDetails> {

        if (!cacheIsDirty && numberDetailsCache != null && numberDetailsCache?.name == numberName)
            return Single.just(numberDetailsCache)

        return getAndSaveNumberDetailsFromRemoteSource(numberName)
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
    fun getNumberDetails(numberName: String, callback: NumberDetailsFetchedCallback) {
        fetchNumberDetails(numberName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ fetchedDetails ->
                if (fetchedDetails != null)
                    callback.onNumberDetailsFetched(fetchedDetails)
                else
                    callback.onError(null)

            },
                {
                    callback.onError(it)
                })
    }

    private fun getAndSaveNumberDetailsFromRemoteSource(numberName: String): Single<NumberDetails> {
        return numberDetailsDataSource.fetchNumberDetails(numberName)
            .doOnSuccess { numberDetails ->
                cacheIsDirty = false
                numberDetailsCache = numberDetails
            }
    }

    fun refresh() {
        cacheIsDirty = true
    }

    interface NumberDetailsFetchedCallback {
        fun onNumberDetailsFetched(numberDetails: NumberDetails)
        fun onError(throwable: Throwable?)
    }
}