package com.macruware.horoscopeapp.data

import android.util.Log
import com.macruware.horoscopeapp.data.network.HoroscopeApiService
import com.macruware.horoscopeapp.domain.Repository
import com.macruware.horoscopeapp.domain.model.PredictionModel
import javax.inject.Inject

// Necesita una referencia a la ApiService la cual es provista por dagger hilt
class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService): Repository {

    // Método sobreescrito de la interfaz Repository
    override suspend fun getPrediction(sign: String): PredictionModel? {
        // Este runCatching es un bloque que nos ayuda a ejecutar la llamada a la API
        // Ejecuta el código que esté dentro y nos brinda dos métodos: onSuccess y onFailure
        runCatching { apiService.getHoroscopePrediction(sign) }
            // En caso de que la llamada salga bien, it contendrá el valor de la respuesta, el cual
            // es un objeto de tipo PredictionResponse, ejecutamos la función que mapea el tipo a PredictionModel
            // y es el valor que retornaremos a la capa de domain
            .onSuccess { return it.toDomain() }
            // En caso de error mandamos un log con el mensaje de error
            .onFailure { Log.e("MYTAG", "Ha ocurrido un error en la llamada: ${it.message}") }
        // En caso de error, retorna null
        return null
    }
}