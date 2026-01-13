package com.example.aplikasidonasi.repository

import com.example.aplikasidonasi.apiservice.ApiService
import com.example.aplikasidonasi.model.Donasi

class DonasiRepository(
    private val apiService: ApiService
) {

    suspend fun getDaftarDonasi(): List<Donasi> {
        return apiService.getDonasi()
    }

    suspend fun getDetailDonasi(id: Int): Donasi {
        return apiService.getDetailDonasi(id)
    }

    suspend fun kirimDonasi(request: DonasiRequest): BaseResponse {
        return apiService.kirimDonasi(request)
    }
}