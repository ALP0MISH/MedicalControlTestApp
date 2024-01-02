package com.example.realmlessons.presentation.main_screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realmlessons.R
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.presentation.components.CameraList
import com.example.realmlessons.presentation.components.DoorList
import com.example.realmlessons.presentation.components.ErrorScreen
import com.example.realmlessons.presentation.components.LoadingScreen
import com.example.realmlessons.presentation.theme.ExtraMediumSpacing
import com.example.realmlessons.presentation.theme.ExtraSpacing
import com.example.realmlessons.presentation.theme.GrayLight
import com.example.realmlessons.presentation.theme.LightBlue
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onSavaClick: (CameraDomain) -> Unit,
    uiStateFlow: StateFlow<MainUiState>
) {
    val uiState by uiStateFlow.collectAsStateWithLifecycle()
    val fullScreenModifier = Modifier
        .fillMaxSize()
        .background(GrayLight)
    when (uiState) {
        is MainUiState.Loading -> LoadingScreen()
        is MainUiState.Error -> {
            val errorState = uiState as MainUiState.Error
            ErrorScreen(message = errorState.message)
        }

        is MainUiState.LoadedScreen -> {
            val loadedState = uiState as MainUiState.LoadedScreen
            LoadedScreen(
                modifier = fullScreenModifier,
                cameraUiState = loadedState,
                onSavaClick = onSavaClick
            )
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LoadedScreen(
    cameraUiState: MainUiState.LoadedScreen,
    onSavaClick: (CameraDomain) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeList = listOf(
        cameraUiState.camera,
        cameraUiState.door
    )

    val pagerState = rememberPagerState { homeList.size }
    val coroutineScope = rememberCoroutineScope()

    val defaultIndicator: @Composable (List<TabPosition>) -> Unit = { tabPositions ->
        Box(
            Modifier
                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                .background(LightBlue)
                .fillMaxWidth()
                .height(2.dp)
        )
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.my_house),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = defaultIndicator,
            ) {
                homeList.forEachIndexed { index, _ ->
                    val header = getPagerHeaderByPosition(index)
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = header,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) {
                when (pagerState.currentPage) {
                    0 -> CameraList(
                        camera = cameraUiState,
                        onSavaClick = onSavaClick
                    )

                    1 -> DoorList(
                        doorDomain = cameraUiState,
                    )
                }
            }
        }
    }
}

@Composable
fun getPagerHeaderByPosition(position: Int): String = when (position) {
    0 -> "камеры"
    else -> "двери"
}
