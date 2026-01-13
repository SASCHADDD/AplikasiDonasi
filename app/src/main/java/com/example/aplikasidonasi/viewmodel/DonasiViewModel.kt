package com.example.aplikasidonasi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.Donasi
import com.example.aplikasidonasi.model.TempatDonasi
import com.example.aplikasidonasi.repository.DonasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DonasiViewModel(
    private val repository: DonasiRepository
) : ViewModel() {

    private val _tempatDonasi = MutableStateFlow<List<TempatDonasi>>(emptyList())
    val tempatDonasi: StateFlow<List<TempatDonasi>> = _tempatDonasi

    private val _riwayatDonasi = MutableStateFlow<List<Donasi>>(emptyList())
    val riwayatDonasi: StateFlow<List<Donasi>> = _riwayatDonasi

    fun getTempatDonasi() {
        viewModelScope.launch {
            _tempatDonasi.value = repository.getTempatDonasi()
        }
    }

    fun getRiwayatDonasi() {
        viewModelScope.launch {
            _riwayatDonasi.value = repository.getRiwayatDonasi()
        }
    }

    fun kirimDonasi(
        tempatId: Int,
        nominal: Long,
        buktiTransfer: ByteArray
    ) {
        viewModelScope.launch {
            repository.kirimDonasi(tempatId, nominal, buktiTransfer)
        }
    }
}