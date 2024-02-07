package com.macruware.horoscopeapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.macruware.horoscopeapp.data.providers.HoroscopeProvider
import com.macruware.horoscopeapp.domain.model.HoroscopeInfo
import com.macruware.horoscopeapp.domain.model.HoroscopeModel
import com.macruware.horoscopeapp.ui.detail.HoroscopeDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    horoscopeProvider: HoroscopeProvider, ) :ViewModel() {
//    private val horoscopeDetailViewModel: HoroscopeDetailViewModel) :ViewModel() {

    private var _horoscopeList = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscopeList : StateFlow<List<HoroscopeInfo>> get() = _horoscopeList

    init {
        _horoscopeList.value = horoscopeProvider.getHoroscopeList()
    }

//    fun setSelectedItem(item: HoroscopeModel){
//        horoscopeDetailViewModel.setCurrentHoroscope(item.name)
//    }
}