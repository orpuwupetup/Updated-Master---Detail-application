package com.orpuwupetup.numberslight.data.source.remote

import com.orpuwupetup.numberslight.api.NumbersLightService
import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.source.NumbersDataSource
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class NumbersRemoteDataSource(private val numbersService: NumbersLightService): NumbersDataSource {

    override fun getNumbersJSON(): Single<List<Number>> =
            numbersService.getNumbersJson()
}