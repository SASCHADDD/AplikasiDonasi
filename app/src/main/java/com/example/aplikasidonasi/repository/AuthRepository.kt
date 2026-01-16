package com.example.aplikasidonasi.repository

import com.example.aplikasidonasi.apiservice.ApiConfig
import com.example.aplikasidonasi.model.Login
import com.example.aplikasidonasi.model.User

class AuthRepository {

    private val apiService = ApiConfig.getApiService()

    suspend fun login(email: String, password: String): User {
        val user = apiService.login(Login(email, password))

        return user ?: throw Exception("Email atau password salah")
    }

    suspend fun register(user: User): User {
        return apiService.register(user)
    }
}