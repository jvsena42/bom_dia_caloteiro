package com.bulletapps.bomdiacaloteiro.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulletapps.bomdiacaloteiro.ui.screens.selectMeme.ScreenSelectMeme
import com.bulletapps.bomdiacaloteiro.util.setNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setNavigation(
                startDestination = MainViewModel.Navigation.SelectMeme.router,
                navGraphBuilder = ::navigationBuilder,
                navEventFlow = mainViewModel.eventFlow,
                navEvent = ::navEvent
            )
        }
    }

    private fun navigationBuilder(builder: NavGraphBuilder) = builder.apply {
        composable(MainViewModel.Navigation.SelectMeme.router) {
            ScreenSelectMeme(
                navigateToMessageInfo = {
                    mainViewModel.navigate(MainViewModel.Navigation.MessageInfo)
                }
            )
        }

    }

    private fun navEvent(navController: NavController, navScreen: MainViewModel.Navigation) {
        navController.navigate(route = navScreen.router) {
            launchSingleTop = true
        }
    }
}
