package com.example.realmlessons.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realmlessons.presentation.main_screen.MainScreen
import com.example.realmlessons.presentation.main_screen.MainViewModel
import com.example.realmlessons.presentation.theme.RealmLessonsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealmLessonsTheme {
                val viewModel: MainViewModel = hiltViewModel()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen(
                        uiStateFlow = viewModel.uiStateFlow.collectAsStateWithLifecycle(),
                        onSavaClick = viewModel::addOrDeleteCamera
                    )
                }
            }
        }
    }
}