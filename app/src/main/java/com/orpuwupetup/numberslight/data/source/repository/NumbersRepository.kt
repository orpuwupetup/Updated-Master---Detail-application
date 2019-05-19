package com.orpuwupetup.numberslight.data.source.repository

import android.util.Log
import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.source.NumbersDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumbersRepository @Inject constructor(private val numbersDataSource: NumbersDataSource) : NumbersDataSource {

    private var cacheIsDirty = false

    // cache, to limit numbers of http calls and to provide data faster
    private val numbersCache: MutableList<Number> = mutableListOf()

    override fun getNumbersJSON(): Single<List<Number>> {

        if (!cacheIsDirty && numbersCache.isNotEmpty()) {
            return Single.just(numbersCache as List<Number>)
        }

        return getAndSaveNumbersFromRemoteSource()
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
}