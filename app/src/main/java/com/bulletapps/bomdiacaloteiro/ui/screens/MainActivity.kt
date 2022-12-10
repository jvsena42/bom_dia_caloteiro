package com.bulletapps.bomdiacaloteiro.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.bulletapps.bomdiacaloteiro.util.setNavigation

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
}

private fun navigationBuilder(builder: NavGraphBuilder) = builder.apply {

}

private fun navEvent(navController: NavController, navScreen: MainViewModel.Navigation) {
    navController.navigate(route = navScreen.router) {
        launchSingleTop = true
    }
}
