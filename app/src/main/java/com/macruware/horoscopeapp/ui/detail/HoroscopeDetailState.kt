package com.macruware.horoscopeapp.ui.detail

import com.macruware.horoscopeapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState{
    data object Loading:HoroscopeDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel):HoroscopeDetailState()
    data class Error(val error: String):HoroscopeDetailState()

}
