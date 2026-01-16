package com.example.aplikasidonasi.repository

class DefaultContainerApp : ContainerApp {

    override val authRepository: AuthRepository by lazy {
        AuthRepository()
    }

    override val donasiRepository: DonasiRepository by lazy {
        DonasiRepository()
    }
}