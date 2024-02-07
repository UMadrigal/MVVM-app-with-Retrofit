package com.macruware.horoscopeapp.data.network

import com.macruware.horoscopeapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    @GET("/{sign}")
    suspend fun getHoroscopePrediction(@Path("sign") sign: String): PredictionResponse

}