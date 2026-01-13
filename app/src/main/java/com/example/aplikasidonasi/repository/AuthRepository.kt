package com.example.aplikasidonasi.repository

import com.example.aplikasidonasi.apiservice.ApiConfig
import com.example.aplikasidonasi.model.Login
import com.example.aplikasidonasi.model.User

class AuthRepository {

    private val apiService = ApiConfig.getApiService()

    suspend fun login(email: String, password: String): User {
        val loginData = Login(email, password)
        return apiService.login(loginData)
    }

    suspend fun register(user: User): User {
        return apiService.register(user)
    }
}
