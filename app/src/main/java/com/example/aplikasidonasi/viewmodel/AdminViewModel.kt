package com.example.aplikasidonasi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.Donasi
import com.example.aplikasidonasi.model.LaporanDonasi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(
    private val repository: AdminRepository
) : ViewModel() {

    private val _laporan = MutableStateFlow<LaporanDonasi?>(null)
    val laporan: StateFlow<LaporanDonasi?> = _laporan

    private val _donasiPending = MutableStateFlow<List<Donasi>>(emptyList())
    val donasiPending: StateFlow<List<Donasi>> = _donasiPending

    fun getLaporan(periode: String) {
        viewModelScope.launch {
            _laporan.value = repository.getLaporan(periode)
        }
    }

    fun getDonasiPending() {
        viewModelScope.launch {
            _donasiPending.value = repository.getDonasiPending()
        }
    }

    fun verifikasiDonasi(donasiId: Int) {
        viewModelScope.launch {
            repository.verifikasiDonasi(donasiId)
        }
    }
}