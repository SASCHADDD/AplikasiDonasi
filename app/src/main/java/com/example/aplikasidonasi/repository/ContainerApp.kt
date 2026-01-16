package com.example.aplikasidonasi.repository

interface ContainerApp {
    val authRepository: AuthRepository
    val donasiRepository: DonasiRepository
}