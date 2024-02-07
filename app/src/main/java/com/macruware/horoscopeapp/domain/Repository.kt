package com.macruware.horoscopeapp.domain

import com.macruware.horoscopeapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}