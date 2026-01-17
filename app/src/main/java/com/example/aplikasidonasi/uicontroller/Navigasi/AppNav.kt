package com.example.aplikasidonasi.uicontroller.Navigasi

import android.app.Application
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplikasidonasi.view.admin.AdminHomeScreen
import com.example.aplikasidonasi.view.admin.AdminTambahTempatDonasiScreen
import com.example.aplikasidonasi.view.auth.HalamanLogin
import com.example.aplikasidonasi.view.auth.HalamanRegister
import com.example.aplikasidonasi.view.user.HalamanDetailDonasi
import com.example.aplikasidonasi.view.user.HalamanKirimDonasi
import com.example.aplikasidonasi.view.user.HalamanRiwayatDonasi
import com.example.aplikasidonasi.view.user.HomeScreenUser
import com.example.aplikasidonasi.view.user.bottomnavitem.BottomNavBar
import com.example.aplikasidonasi.view.user.topbar.HomeTopBar
import com.example.aplikasidonasi.viewmodel.AuthViewModel
import com.example.aplikasidonasi.viewmodel.Provider.PenyediaViewModel

@Composable
fun DonasiApp() {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val authViewModel: AuthViewModel = viewModel(
        factory = PenyediaViewModel.provideAuthViewModel(application)
    )
    val token by authViewModel.token.collectAsState()

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val role by authViewModel.role.collectAsState()

    Scaffold(
        topBar = {
            if (currentRoute == PetaNavigasi.HOME) {
                HomeTopBar(
                    query = "",
                    onQueryChange = {},
                    onProfileClick = {}
                )
            }
        },
        bottomBar = {
            if (
                currentRoute == PetaNavigasi.HOME ||
                currentRoute == PetaNavigasi.RIWAYAT
            ) {
                BottomNavBar(navController, currentRoute)
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = PetaNavigasi.LOGIN
        ) {

            composable(PetaNavigasi.LOGIN) {
                HalamanLogin(
                    authViewModel = authViewModel,
                    onLoginSuccess = { role ->
                        when (role) {
                            "admin" -> navController.navigate(PetaNavigasi.ADMIN_HOME) {
                                popUpTo(PetaNavigasi.LOGIN) { inclusive = true }
                            }

                            else -> navController.navigate(PetaNavigasi.HOME) {
                                popUpTo(PetaNavigasi.LOGIN) { inclusive = true }
                            }
                        }
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
                        navController.navigate(PetaNavigasi.LOGIN) {
                            popUpTo(PetaNavigasi.REGISTER) { inclusive = true }
                        }
                    }
                )
            }

            composable(PetaNavigasi.HOME) {
                HomeScreenUser(
                    paddingValues = paddingValues,
                    onItemClick = { id ->
                        navController.navigate(PetaNavigasi.detailRoute(id))
                    },
                    onProfileClick = {}
                )
            }

            composable(
                route = PetaNavigasi.DETAIL,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                HalamanDetailDonasi(
                    id = id,
                    onDonasiClick = { donasiId ->
                        navController.navigate(PetaNavigasi.kirimDonasiRoute(donasiId))
                    }
                )
            }

            composable(
                route = PetaNavigasi.KIRIMDONASI,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                HalamanKirimDonasi(
                    token = token,
                    donasiId = id,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(PetaNavigasi.RIWAYAT) {
                HalamanRiwayatDonasi(
                    token = token ?: return@composable,
                    paddingValues = paddingValues
                )
            }

            composable(PetaNavigasi.ADMIN_HOME) {
                AdminHomeScreen(
                    paddingValues = paddingValues,
                    onAddClick = {
                        navController.navigate(PetaNavigasi.ADMIN_TAMBAH_DONASI)
                    },
                    onItemClick = {}
                )
            }

            composable(PetaNavigasi.ADMIN_TAMBAH_DONASI) {
                AdminTambahTempatDonasiScreen(
                    token = token,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}