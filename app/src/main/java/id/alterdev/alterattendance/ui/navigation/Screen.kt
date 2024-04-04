package id.alterdev.alterattendance.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Scanner : Screen("scanner")
    data object History : Screen("history")
    data object Profile : Screen("profile")
}