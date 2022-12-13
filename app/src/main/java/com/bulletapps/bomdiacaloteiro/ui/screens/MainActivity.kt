package com.bulletapps.bomdiacaloteiro.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo.ScreenMessageInfo
import com.bulletapps.bomdiacaloteiro.ui.screens.selectMeme.ScreenSelectMeme
import com.bulletapps.bomdiacaloteiro.util.getBitmapFromResource
import com.bulletapps.bomdiacaloteiro.util.getImageUri
import com.bulletapps.bomdiacaloteiro.util.getUriResource
import com.bulletapps.bomdiacaloteiro.util.intentShare
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
                navigateToMessageInfo = { imageRef ->
                    mainViewModel.selectedMemeRef = imageRef
                    mainViewModel.navigate(MainViewModel.Navigation.MessageInfo)
                }
            )
        }

        composable(MainViewModel.Navigation.MessageInfo.router) {
            ScreenMessageInfo(selectedMemeRef = mainViewModel.selectedMemeRef) { text, imageRef ->
                val intent = intentShare(text, getImageUri(imageRef))
                Intent.createChooser(intent, null)
                startActivity(intent)
            }
        }
    }

    private fun navEvent(navController: NavController, navScreen: MainViewModel.Navigation) {
        navController.navigate(route = navScreen.router) {
            launchSingleTop = true
        }
    }
}
