package com.orpuwupetup.numberslight.api

import com.orpuwupetup.numberslight.data.model.number.Number
import io.reactivex.Single
import retrofit2.http.GET

interface NumbersLightService {

    @GET("json.php")
    fun getNumbersJson(): Single<List<Number>>
}