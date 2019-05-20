package com.orpuwupetup.numberslight.api

import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.data.model.number.details.NumberDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NumbersLightService {

    @GET("json.php")
    fun getNumbersJson(): Single<List<Number>>

    @GET("json.php")
    fun getNumberDetails(@Query("name") numberName: String): Single<NumberDetails>
}