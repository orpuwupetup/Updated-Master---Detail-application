package com.orpuwupetup.numberslight.data.source

import com.orpuwupetup.numberslight.data.model.number.Number
import io.reactivex.Single

interface NumbersDataSource {

    fun fetchNumbers(): Single<List<Number>>
}