package com.example.aplikasidonasi.viewmodel.Provider

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasidonasi.AplikasiDonasi
import com.example.aplikasidonasi.viewmodel.AuthViewModel

object PenyediaViewModel {

    fun provideAuthViewModel(application: Application): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val container =
                    (application as AplikasiDonasi).container
                return AuthViewModel(
                    repository = container.authRepository
                ) as T
            }
        }
}
