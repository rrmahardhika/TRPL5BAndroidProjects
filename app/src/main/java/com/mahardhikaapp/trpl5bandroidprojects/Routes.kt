package com.mahardhikaapp.trpl5bandroidprojects

sealed class Routes(val route: String){
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Setting : Routes("setting")
}