package com.example.aplikasidonasi.apiservice

import com.example.aplikasidonasi.model.Login
import com.example.aplikasidonasi.model.LoginResponse
import com.example.aplikasidonasi.model.RiwayatDonasi
import com.example.aplikasidonasi.model.TempatDonasi
import com.example.aplikasidonasi.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    ): LoginResponse

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

    @GET("riwayat")
    suspend fun getRiwayatDonasi(
        @Header("Authorization") token: String
    ): List<RiwayatDonasi>

    @Multipart
    @POST("admin/tempat-donasi")
    suspend fun tambahTempatDonasi(
        @Header("Authorization") token: String,
        @Part("nama") nama: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("target") target: RequestBody,
        @Part foto: MultipartBody.Part
    )

}
