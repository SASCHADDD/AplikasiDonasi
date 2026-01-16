package com.example.aplikasidonasi.apiservice

import com.example.aplikasidonasi.model.Login
import com.example.aplikasidonasi.model.RiwayatDonasi
import com.example.aplikasidonasi.model.TempatDonasi
import com.example.aplikasidonasi.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {

    // ===== AUTH =====
    @POST("auth/login")
    suspend fun login(
        @Body login: Login
    ): User

    @POST("auth/register")
    suspend fun register(
        @Body user: User
    ): User

    // ===== TEMPAT DONASI =====
    @GET("tempat-donasi")
    suspend fun getTempatDonasi(): List<TempatDonasi>

    @GET("tempat-donasi/{id}")
    suspend fun getDetailTempatDonasi(
        @Path("id") id: Int
    ): TempatDonasi

    @Multipart
    @POST("donasi")
    suspend fun kirimDonasi(
        @Header("Authorization") token: String,
        @Part("tempat_id") tempatId: RequestBody,
        @Part("nominal") nominal: RequestBody,
        @Part bukti_transfer: MultipartBody.Part
    )

    @GET("/riwayat")
    suspend fun getRiwayatDonasi(
        @Header("Authorization") token: String
    ): List<RiwayatDonasi>
}
