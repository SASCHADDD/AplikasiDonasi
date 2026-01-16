package com.example.aplikasidonasi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.TempatDonasi
import com.example.aplikasidonasi.repository.DonasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailDonasiViewModel : ViewModel() {

    private val repository = DonasiRepository()

    private val _detailDonasi = MutableStateFlow<TempatDonasi?>(null)
    val detailDonasi: StateFlow<TempatDonasi?> = _detailDonasi

    fun loadDetailDonasi(id: Int) {
        viewModelScope.launch {
            try {
                _detailDonasi.value = repository.getDetailTempatDonasi(id)
            } catch (e: Exception) {
                _detailDonasi.value = null
            }
        }
    }
}