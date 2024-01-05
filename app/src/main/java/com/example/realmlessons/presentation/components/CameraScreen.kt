package com.example.realmlessons.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.realmlessons.R
import com.example.realmlessons.presentation.main_screen.MainUiState
import com.example.realmlessons.presentation.models.Camera
import com.example.realmlessons.presentation.models.CameraMark
import com.example.realmlessons.presentation.theme.ExtraLargeSpacing
import com.example.realmlessons.presentation.theme.ExtraMediumSpacing
import com.example.realmlessons.presentation.theme.LargeSpacing
import com.example.realmlessons.presentation.theme.MediumSpacing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


@Composable
fun CameraList(
    camera: MainUiState.LoadedScreen,
    onSavaClick: (CameraMark) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(21.dp),
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(bottom = ExtraMediumSpacing)
                    .padding(),
                text = stringResource(id = R.string.living_room),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        items(
            items = camera.cameraMarks,
            key = { it.camera.id },
        ) { cameraMark ->
            CameraItem(
                cameraMark = cameraMark,
                onSavaClick = onSavaClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CameraItem(
    cameraMark: CameraMark,
    onSavaClick: (CameraMark) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val swipableState = rememberSwipeableState(initialValue = 0)
    val isSwiped by rememberUpdatedState(swipableState.currentValue.toFloat() != 0f)
    val camera = cameraMark.camera
    val listener = object : ImageRequest.Listener {
        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
        }

        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
            super.onSuccess(request, result)
        }
    }

    var isPressed by remember { mutableStateOf(false) }
    var isHidden by remember { mutableStateOf(false) }


    val imageRequest = ImageRequest.Builder(context).data(camera.snapshot).listener(listener)
        .dispatcher(Dispatchers.IO).memoryCacheKey(camera.snapshot).diskCacheKey(camera.snapshot)
        .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED).build()

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(2000)
            isPressed = false
            isHidden = false
        }
    }

    Box(
        modifier = modifier
            .padding(bottom = MediumSpacing)
            .padding(top = MediumSpacing)
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(ExtraMediumSpacing))
            .swipeable(
                state = swipableState, anchors = mapOf(
                    0f to 0,
                    -dpToPx(dp = 50f, context = context) to 1,
                ), thresholds = { _, _ ->
                    FractionalThreshold(0.3f)
                }, orientation = Orientation.Horizontal
            )
    ) {
        Box(modifier = Modifier
            .offset {
                IntOffset(swipableState.offset.value.roundToInt(), 0)
            }
            .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.Center),
                model = imageRequest,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .size(ExtraLargeSpacing)
                    .align(Alignment.TopStart),
                painter = painterResource(id = R.drawable.rec),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(start = LargeSpacing),
                    text = camera.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        AnimatedVisibility(
            visible = isSwiped,
            enter = fadeIn(animationSpec = tween(durationMillis = 20)),
            exit = fadeOut(animationSpec = tween(durationMillis = 20)),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(36.dp)
                .alpha(if (isSwiped) 1f else 0.5f)
                .padding(end = MediumSpacing)
                .padding(bottom = ExtraMediumSpacing)

        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        onSavaClick(cameraMark)
                    }
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = if (cameraMark.isSaved) painterResource(id = R.drawable.star)
                    else painterResource(id = R.drawable.stars),
                    contentDescription = null,
                )
            }
        }
    }
}


@Preview
@Composable
fun CameraItemPreview() {
    MaterialTheme {
        CameraItem(
            cameraMark = CameraMark.unknown.copy(
                camera = Camera.unknown.copy(
                    snapshot = "https://w.forfun.com/fetch/b4/b4061f05d1365648decb0cac791373a3.jpeg",
                    name = stringResource(id = R.string.my_house)
                )
            ),
            onSavaClick = {},
        )
    }
}