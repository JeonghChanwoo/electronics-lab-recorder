package com.electronics.labrecorder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.electronics.labrecorder.ui.screens.home.HomeScreen
import com.electronics.labrecorder.ui.screens.circuit.CircuitSimulatorScreen
import com.electronics.labrecorder.ui.screens.experiment.ExperimentRecorderScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavigationItem.CircuitSimulator.route) {
            CircuitSimulatorScreen(navController = navController)
        }
        composable(NavigationItem.ExperimentRecorder.route) {
            ExperimentRecorderScreen(navController = navController)
        }
    }
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem("home")
    object CircuitSimulator : NavigationItem("circuit_simulator")
    object ExperimentRecorder : NavigationItem("experiment_recorder")
}