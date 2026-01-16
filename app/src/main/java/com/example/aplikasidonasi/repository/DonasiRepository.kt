package com.example.aplikasidonasi.repository

import com.example.aplikasidonasi.apiservice.ApiConfig
import com.example.aplikasidonasi.model.RiwayatDonasi
import com.example.aplikasidonasi.model.TempatDonasi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class DonasiRepository {

    private val apiService = ApiConfig.getApiService()

    private val _listDonasi = MutableStateFlow<List<TempatDonasi>>(emptyList())
    val listDonasi: StateFlow<List<TempatDonasi>> = _listDonasi

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _riwayat = MutableStateFlow<List<RiwayatDonasi>>(emptyList())
    val riwayat: StateFlow<List<RiwayatDonasi>> = _riwayat

    suspend fun fetchListTempatDonasi() {
        _loading.value = true
        try {
            _listDonasi.value = apiService.getTempatDonasi()
        } catch (e: Exception) {
            e.printStackTrace()
            _listDonasi.value = emptyList()
        } finally {
            _loading.value = false
        }
    }
    suspend fun getListTempatDonasi() {
        _loading.value = true
        try {
            val response = apiService.getTempatDonasi()
            _listDonasi.value = response
        } catch (e: Exception) {
            e.printStackTrace()
            _listDonasi.value = emptyList()
        } finally {
            _loading.value = false
        }
    }

    suspend fun getDetailTempatDonasi(id: Int): TempatDonasi {
        return apiService.getDetailTempatDonasi(id)
    }

    suspend fun submitDonasi(
        token: String,
        tempatId: Int,
        nominal: Int,
        bukti: MultipartBody.Part
    ) {
        apiService.kirimDonasi(
            token = "Bearer $token",
            tempatId = tempatId.toString().toRequestBody(),
            nominal = nominal.toString().toRequestBody(),
            bukti_transfer = bukti
        )
    }
    suspend fun getRiwayatDonasi(token: String): List<RiwayatDonasi> {
        return apiService.getRiwayatDonasi("Bearer $token")
    }
}
