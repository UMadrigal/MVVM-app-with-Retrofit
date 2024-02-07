package com.macruware.horoscopeapp.data.viewModelModule

import com.macruware.horoscopeapp.domain.usecase.GetPredictionUseCase
import com.macruware.horoscopeapp.ui.detail.HoroscopeDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

//    @Singleton
//    @Provides
//    fun provideHoroscopeDetailViewModel(
//        getPredictionUseCase: GetPredictionUseCase): HoroscopeDetailViewModel {
//        return HoroscopeDetailViewModel(getPredictionUseCase)
//    }
}