package com.orpuwupetup.numberslight.data.model.number.details

import com.google.gson.annotations.SerializedName

data class NumberDetails(
    val name: String,
    val text: String,

    @SerializedName("image")
    val imageUrl: String
)
