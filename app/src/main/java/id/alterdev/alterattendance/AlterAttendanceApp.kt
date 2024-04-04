package id.alterdev.alterattendance

import android.Manifest
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import id.alterdev.alterattendance.features.history.AttendanceHistoryScreen
import id.alterdev.alterattendance.features.home.HomeScreen
import id.alterdev.alterattendance.features.home.HomeViewModel
import id.alterdev.alterattendance.features.scan.ScannerScreen
import id.alterdev.alterattendance.ui.navigation.BottomNavItem
import id.alterdev.alterattendance.ui.navigation.Screen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AlterAttendanceApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val viewModel: HomeViewModel = viewModel()

    Scaffold(
        bottomBar = {
            if (currentRoute !in listOf(
                    Screen.Scanner.route
                )
            ) {
                BototmBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.Scanner.route) {
                val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

                if (cameraPermissionState.status.isGranted){
                    ScannerScreen(navController = navController)
                } else if (cameraPermissionState.status.shouldShowRationale){
                    Text(text = "Camera Permission permanently denied")
                } else {
                    SideEffect {
                        cameraPermissionState.run { launchPermissionRequest() }
                    }
                    Text(text = "No Camera Permission")
                }
            }
            composable(Screen.History.route) {
                AttendanceHistoryScreen()
            }
            composable(Screen.Profile.route) {
                Text("Profile")
            }
        }
    }

}

@Composable
private fun BototmBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            BottomNavItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            BottomNavItem(
                title = "History",
                icon = Icons.Default.BarChart,
                screen = Screen.History
            ),
            BottomNavItem(
                title = "Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}