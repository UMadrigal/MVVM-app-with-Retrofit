package com.macruware.horoscopeapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.macruware.horoscopeapp.domain.model.PredictionModel

data class PredictionResponse(
    @SerializedName("horoscope")
    val prediction: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sign")
    val sign: String
){
    fun toDomain(): PredictionModel{
        return PredictionModel(prediction = prediction, sign = sign)
    }
}