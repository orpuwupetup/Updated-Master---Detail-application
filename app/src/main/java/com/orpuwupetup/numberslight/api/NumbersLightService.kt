package com.orpuwupetup.numberslight.api

import retrofit2.http.GET

interface NumbersLightService {

    @GET("json.php")
    fun getNumbersJson(): String
}