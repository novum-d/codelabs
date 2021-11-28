package com.example.compose.rally


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.compose.rally.RallyScreen.Accounts
import com.example.compose.rally.RallyScreen.Bills
import com.example.compose.rally.RallyScreen.Overview
import com.example.compose.rally.data.UserData
import com.example.compose.rally.ui.accounts.AccountsBody
import com.example.compose.rally.ui.accounts.SingleAccountBody
import com.example.compose.rally.ui.bills.BillsBody
import com.example.compose.rally.ui.components.RallyTabRow
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val navController = rememberNavController() // Get NavController

        val allScreens = RallyScreen.values().toList()
        var currentScreen by rememberSaveable {
            mutableStateOf(RallyScreen.Overview)
        }

        Scaffold(
          topBar = {
              RallyTabRow(
                allScreens = allScreens,
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
              )
          }
        ) { innerPadding ->
            NavHost(
              navController = navController,
              startDestination = Overview.name,
              modifier = Modifier.padding(innerPadding)
            ) {
                composable(Overview.name) {
                    Text(Overview.name)
                }
                composable(Accounts.name) {
                    Text(Accounts.name)
                }
                composable(Bills.name) {
                    Text(Bills.name)
                }
            }
        }
    }
}
