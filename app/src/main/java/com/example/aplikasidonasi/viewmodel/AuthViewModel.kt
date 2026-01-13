package com.example.aplikasidonasi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.User
import com.example.aplikasidonasi.model.request.LoginRequest
import com.example.aplikasidonasi.model.request.RegisterRequest
import com.example.aplikasidonasi.model.response.LoginResponse
import com.example.aplikasidonasi.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    var user by mutableStateOf<User?>(null)
        private set

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            try {
                user = repository.login(request)
            } catch (e: Exception) {
                // error ditangani di UI
            }
        }
    }
}