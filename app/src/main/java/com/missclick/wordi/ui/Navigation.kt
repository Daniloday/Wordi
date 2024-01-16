package com.missclick.wordi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.missclick.wordi.ui.screens.menu.Menu
import com.missclick.wordi.ui.screens.words.Words

@Composable
fun Navigation() {

    val navController = rememberNavController()



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    )
    {
        NavHost(navController = navController, startDestination = Direction.MENU.name) {
            composable(Direction.MENU.name) {
                Menu(navController)
            }
            composable(Direction.DAILY.name) {
                Words(navController,daily = true)
            }
            composable(Direction.RANDOM.name) {
                Words(navController,daily = false)
            }
            composable(Direction.LANGUAGE.name) {

            }
            composable(Direction.INFO.name) {

            }
        }
    }





}


enum class Direction {
    MENU, DAILY, RANDOM, LANGUAGE, INFO
}