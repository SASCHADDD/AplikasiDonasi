package com.example.aplikasidonasi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.apiservice.ApiConfig
import com.example.aplikasidonasi.model.RiwayatDonasi
import com.example.aplikasidonasi.repository.DonasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RiwayatDonasiViewModel : ViewModel() {

    private val repository = DonasiRepository()

    private val _riwayat = MutableLiveData<List<RiwayatDonasi>>()
    val riwayat: LiveData<List<RiwayatDonasi>> = _riwayat

    fun loadRiwayat(token: String) {
        viewModelScope.launch {
            try {
                _riwayat.value = repository.getRiwayatDonasi(token)
            } catch (e: HttpException) {
                _riwayat.value = emptyList()
            } catch (e: Exception) {
                _riwayat.value = emptyList()
            }
        }
    }
}