package com.example.aplikasidonasi

import android.app.Application
import com.example.aplikasidonasi.repository.ContainerApp
import com.example.aplikasidonasi.repository.DefaultContainerApp

class AplikasiDonasi : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainerApp()
    }
}