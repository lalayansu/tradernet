package com.example.presenter

import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presenter.extensions.paddingValuesVectorConverter
import com.example.presenter.navigation.Destinations
import com.example.presenter.theme.TradernetTheme
import com.example.presenter.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TradernetApp(
    startRoute: String
) {
    val navController = rememberNavController()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = rememberTopAppBarState())
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    TradernetTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        ) { paddingValues ->
            val animatedPaddingValues = animateValueAsState(
                targetValue = when (currentRoute) {
                    Destinations.HOME_SCREEN_DESTINATION -> PaddingValues(0.dp)
                    else -> paddingValues
                },
                typeConverter = paddingValuesVectorConverter(
                    layoutDirection = LocalLayoutDirection.current
                ),
                animationSpec = tween(durationMillis = 300),
                label = ""
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NavHost(
                    modifier = Modifier
                        .imePadding()
                        .navigationBarsPadding()
                        .statusBarsPadding()
                        .padding(paddingValues = animatedPaddingValues.value),
                    navController = navController,
                    startDestination = startRoute,
                ) {
                    composable(Destinations.HOME_SCREEN_DESTINATION) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TradernetAppPreview() {
    TradernetTheme {
        TradernetApp(
            startRoute = Destinations.HOME_SCREEN_DESTINATION,
        )
    }
}