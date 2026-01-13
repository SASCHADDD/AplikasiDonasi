package com.example.aplikasidonasi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.User
import com.example.aplikasidonasi.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _user.value = repository.login(email, password)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    fun register(
        nama: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                _user.value = repository.register(
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