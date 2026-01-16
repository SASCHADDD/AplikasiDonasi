package com.example.aplikasidonasi.view.user.rupiah

fun formatRupiahInput(value: String): String {
    if (value.isBlank()) return ""
    return "Rp " + value
        .toLong()
        .toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}