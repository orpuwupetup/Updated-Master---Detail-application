package com.orpuwupetup.numberslight.data.model.number

import com.google.gson.annotations.SerializedName

data class Number(
    val name: String,

    @SerializedName("image")
    val imageUrl: String
)