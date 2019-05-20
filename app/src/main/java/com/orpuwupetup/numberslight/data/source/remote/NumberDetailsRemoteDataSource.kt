package com.orpuwupetup.numberslight.data.source.remote

import com.orpuwupetup.numberslight.api.NumbersLightService
import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import com.orpuwupetup.numberslight.data.source.NumberDetailsDataSource
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class NumberDetailsRemoteDataSource(private val numberService: NumbersLightService) : NumberDetailsDataSource {

    override fun fetchNumberDetails(numberName: String): Single<NumberDetails> =
        numberService.getNumberDetails(numberName)
}