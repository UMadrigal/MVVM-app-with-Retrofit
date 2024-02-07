package com.macruware.horoscopeapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macruware.horoscopeapp.R
import com.macruware.horoscopeapp.domain.model.HoroscopeModel
import com.macruware.horoscopeapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase) : ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> get() = _state

    lateinit var horoscope: HoroscopeModel

    fun getHoroscopePrediction(sign: HoroscopeModel){
        horoscope = sign
        viewModelScope.launch {

            _state.value = HoroscopeDetailState.Loading

            val result = withContext(IO){ getPredictionUseCase.invoke(sign.name) }

            if (result != null){
                _state.value = HoroscopeDetailState.Success(result.prediction, result.sign, horoscope)
            } else {
                val error = "No ha sido posible obtener la información del servidor"
                _state.value = HoroscopeDetailState.Error(error)
            }
        }
    }
}