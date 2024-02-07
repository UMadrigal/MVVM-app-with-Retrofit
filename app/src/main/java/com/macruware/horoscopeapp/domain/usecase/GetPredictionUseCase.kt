package com.macruware.horoscopeapp.domain.usecase

import com.macruware.horoscopeapp.domain.Repository
import com.macruware.horoscopeapp.domain.model.PredictionModel
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {

    suspend fun invoke(sign:String): PredictionModel? = repository.getPrediction(sign)

}