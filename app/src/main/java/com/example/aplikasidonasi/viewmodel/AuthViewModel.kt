package com.example.aplikasidonasi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.User
import com.example.aplikasidonasi.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    private val _role = MutableStateFlow<String?>(null)
    val role: StateFlow<String?> = _role

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)

                _token.value = response.token
                _role.value = response.role   // ⬅️ INI KUNCINYA

            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    fun register(nama: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                repository.register(
                    User(
                        nama = nama,
                        email = email,
                        password = password
                    )
                )
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
