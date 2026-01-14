package com.example.aplikasidonasi.uicontroller.Navigasi

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplikasidonasi.view.auth.HalamanLogin
import com.example.aplikasidonasi.view.auth.HalamanRegister
import com.example.aplikasidonasi.viewmodel.AuthViewModel
import com.example.aplikasidonasi.viewmodel.Provider.PenyediaViewModel

@Composable
fun DonasiApp() {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val authViewModel: AuthViewModel = viewModel(
        factory = PenyediaViewModel.provideAuthViewModel(application)
    )

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PetaNavigasi.LOGIN
    ) {
        composable(PetaNavigasi.LOGIN) {
            HalamanLogin(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(PetaNavigasi.HOME)
                },
                onRegisterClick = {
                    navController.navigate(PetaNavigasi.REGISTER)
                }
            )
        }

        composable(PetaNavigasi.REGISTER) {
            HalamanRegister(
                authViewModel = authViewModel,
                onRegisterSuccess = {
                    navController.popBackStack()
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )

        }
    }
}