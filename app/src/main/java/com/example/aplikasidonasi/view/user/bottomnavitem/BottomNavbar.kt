package com.example.aplikasidonasi.view.user.bottomnavitem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.aplikasidonasi.uicontroller.Navigasi.PetaNavigasi

@Composable
fun BottomNavBar(
    navController: NavController,
    currentRoute: String?
) {
    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == PetaNavigasi.HOME,
            onClick = {
                navController.navigate(PetaNavigasi.HOME) {
                    popUpTo(PetaNavigasi.HOME)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == PetaNavigasi.RIWAYAT,
            onClick = {
                navController.navigate(PetaNavigasi.RIWAYAT) {
                    popUpTo(PetaNavigasi.HOME)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.History,
                    contentDescription = "Riwayat"
                )
            },
            label = { Text("Riwayat") }
        )
    }
}