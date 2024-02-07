package com.macruware.horoscopeapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.macruware.horoscopeapp.R
import com.macruware.horoscopeapp.databinding.ActivityHoroscopeDetailBinding
import com.macruware.horoscopeapp.domain.model.HoroscopeModel
import com.macruware.horoscopeapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val viewModel: HoroscopeDetailViewModel by viewModels()
//    private lateinit var viewModel: HoroscopeDetailViewModel

//    private val callback = object : OnBackPressedCallback(true){
//        override fun handleOnBackPressed() {
//            onBtnBackPressed()
//        }
//    }

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        onBackPressedDispatcher.addCallback(this,callback)

//        viewModel = ViewModelProvider(this)[HoroscopeDetailViewModel::class.java]

        initData()
        initUI()
    }

    private fun initData() {
        viewModel.getHoroscopePrediction(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun onBtnBackPressed(){
//        onDestroy()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    when(it){
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                        is HoroscopeDetailState.Error -> errorState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when(state.horoscopeModel){
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }

        binding.ivDetail.setImageResource(image)
    }

    private fun errorState(state: HoroscopeDetailState.Error) {
        binding.pb.isVisible = false
        Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
    }
}