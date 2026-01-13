package com.example.aplikasidonasi.repository

import com.example.aplikasidonasi.apiservice.ApiService
import com.example.aplikasidonasi.model.User
import com.example.aplikasidonasi.model.request.LoginRequest
import com.example.aplikasidonasi.model.request.RegisterRequest
import com.example.aplikasidonasi.model.response.LoginResponse
import com.example.aplikasidonasi.model.response.RegisterResponse

class AuthRepository(
    private val apiService: ApiService
) {
    suspend fun login(request: LoginRequest): User {
        return apiService.login(request)
    }

    suspend fun register(request: RegisterRequest): User {
        return apiService.register(request)
    }
}
