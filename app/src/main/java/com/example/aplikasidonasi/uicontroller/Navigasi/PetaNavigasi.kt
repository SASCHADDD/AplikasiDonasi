package com.example.aplikasidonasi.uicontroller.Navigasi

object PetaNavigasi {

    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"

    const val DETAIL = "detail/{id}"
    fun detailRoute(id: Int) = "detail/$id"

    const val KIRIMDONASI = "kirim_donasi/{id}"
    fun kirimDonasiRoute(id: Int) = "kirim_donasi/$id"

    const val RIWAYAT = "riwayat"
}

