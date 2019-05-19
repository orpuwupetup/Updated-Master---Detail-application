package com.orpuwupetup.numberslight.data.source.repository

import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.source.NumbersDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumbersRepository @Inject constructor(private val numbersDataSource: NumbersDataSource): NumbersDataSource {

    override fun getNumbersJSON(): Single<List<Number>> {

        // TODO get and cache data from remote source
        return numbersDataSource.getNumbersJSON()
    }
}