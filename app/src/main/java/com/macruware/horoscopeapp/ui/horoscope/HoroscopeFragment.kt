package com.macruware.horoscopeapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.macruware.horoscopeapp.R
import com.macruware.horoscopeapp.databinding.FragmentHoroscopeBinding
import com.macruware.horoscopeapp.domain.model.HoroscopeInfo
import com.macruware.horoscopeapp.domain.model.HoroscopeInfo.*
import com.macruware.horoscopeapp.domain.model.HoroscopeModel
import com.macruware.horoscopeapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

//    private lateinit var viewModel: HoroscopeViewModel
    private val viewModel: HoroscopeViewModel by viewModels()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
//        viewModel = ViewModelProvider(this)[HoroscopeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {horoscopeInfo: HoroscopeInfo -> onItemSelected(horoscopeInfo) })
        binding.rvHoroscope.apply {
            adapter = horoscopeAdapter
            layoutManager = GridLayoutManager(context,2)
        }
    }

    private fun onItemSelected(horoscopeInfo: HoroscopeInfo){

        val selectedItem = when(horoscopeInfo){
            Aquarius -> HoroscopeModel.Aquarius
            Aries -> HoroscopeModel.Aries
            Cancer -> HoroscopeModel.Cancer
            Capricorn -> HoroscopeModel.Capricorn
            Gemini -> HoroscopeModel.Gemini
            Leo -> HoroscopeModel.Leo
            Libra -> HoroscopeModel.Libra
            Pisces -> HoroscopeModel.Pisces
            Sagittarius -> HoroscopeModel.Sagittarius
            Scorpio -> HoroscopeModel.Scorpio
            Taurus -> HoroscopeModel.Taurus
            Virgo -> HoroscopeModel.Virgo
        }

//        viewModel.setSelectedItem(selectedItem)

//        findNavController().navigate(R.id.action_horoscopeFragment_to_horoscopeDetailActivity)
        findNavController().navigate(
            HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(selectedItem))

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.horoscopeList.collect{ list ->
                    horoscopeAdapter.updateList(list)
                }
            }
        }
    }
}