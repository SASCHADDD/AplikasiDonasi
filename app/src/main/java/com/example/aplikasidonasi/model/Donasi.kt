package com.example.aplikasidonasi.model

data class Donasi(
    val donasi_id: Int = 0,
    val user_id: Int,
    val tempat_id: Int,
    val nominal: Double,
    val bukti_transfer: String,
    val status: String = "pending"
)