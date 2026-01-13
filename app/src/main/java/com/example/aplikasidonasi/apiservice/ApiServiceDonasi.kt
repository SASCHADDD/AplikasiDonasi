package com.example.aplikasidonasi.apiservice

import com.example.aplikasidonasi.model.Donasi
import com.example.aplikasidonasi.model.Login
import com.example.aplikasidonasi.model.TempatDonasi
import com.example.aplikasidonasi.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    // LOGIN
    @POST("login")
    suspend fun login(
        @Body login: Login
    ): User

    // REGISTER
    @POST("register")
    suspend fun register(
        @Body user: User
    ): User

    // LIST TEMPAT DONASI
    @GET("tempat-donasi")
    suspend fun getTempatDonasi(): List<TempatDonasi>

    // KIRIM DONASI
    @POST("donasi")
    suspend fun kirimDonasi(
        @Body donasi: Donasi
    ): Donasi
}