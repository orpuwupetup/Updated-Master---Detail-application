package com.orpuwupetup.numberslight.data.source

import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import io.reactivex.Single

interface NumberDetailsDataSource {

    fun fetchNumberDetails(numberName: String): Single<NumberDetails>

}