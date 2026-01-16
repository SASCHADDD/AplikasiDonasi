package com.example.aplikasidonasi.model

data class TempatDonasi(
    val id: Int,
    val nama: String,
    val deskripsi: String,
    val foto: String?,
    val target: Long,
    val terkumpul: Long
)