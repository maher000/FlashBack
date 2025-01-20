package com.maher.flashback.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.maher.flashback.R
import com.maher.flashback.core.designSystem.theme.FlashBackTheme
import com.maher.flashback.main.ui.appComponents.AppNavigationBar
import com.maher.flashback.main.ui.navigation.FlashBackNavHost
import com.maher.flashback.main.ui.navigation.navigateToMainDestinations

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            FlashBackTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text(stringResource(R.string.title_main))
                        })
                    },
                    bottomBar = {
                        AppNavigationBar(
                            navigateToDestination = {
                                navigateToMainDestinations(screen = it, navController = navController)
                            }
                        )
                    }
                ) { innerPadding ->
                    FlashBackNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                    )
                }
            }
        }
    }
}