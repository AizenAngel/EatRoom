package com.example.eatroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.eatroom.ui.screens.NavGraphs
import com.example.eatroom.ui.theme.EatRoomTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EatRoomTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = MaterialTheme.colors.secondary)
                Surface(color = MaterialTheme.colors.background) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun mainActivity() = LocalContext.current as MainActivity