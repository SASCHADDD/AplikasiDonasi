package com.example.aplikasidonasi.model

data class User(
    val id: Int = 0,
    val nama: String,
    val email: String,
    val password: String = "",
    val role: String = "user",
    val token: String? = null
)