@file:OptIn(ExperimentalMaterialApi::class)

package com.example.realmlessons.presentation.components

import android.content.Context
import android.util.TypedValue
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.realmlessons.R
import com.example.realmlessons.presentation.main_screen.MainUiState
import com.example.realmlessons.presentation.theme.ExtraLargeSpacing
import com.example.realmlessons.presentation.theme.LargeSpacing
import com.example.realmlessons.presentation.theme.MediumSpacing
import kotlin.math.roundToInt

@Composable
fun DoorList(
    doorDomain: MainUiState.LoadedScreen,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(21.dp),
    ) {
        items(
            items = doorDomain.door,
            key = { it.id },
        ) { door ->
            DoorItem(
                doorLock = door.favorites,
                doorTitle = door.name,
                modifier = modifier.padding(MediumSpacing),
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorItem(
    doorTitle: String, doorLock: Boolean,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val swipableState = rememberSwipeableState(initialValue = 0)
    val isSwiped by rememberUpdatedState(swipableState.currentValue.toFloat() != 0f)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .swipeable(
                state = swipableState, anchors = mapOf(
                    0f to 0,
                    -dpToPx(dp = 30f, context = context) to 1,
                ), thresholds = { _, _ ->
                    FractionalThreshold(0.3f)
                }, orientation = Orientation.Horizontal
            )
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(MediumSpacing), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .offset {
                IntOffset(swipableState.offset.value.roundToInt(), 0)
            }
            .fillMaxWidth()) {
            Row(
                modifier = Modifier,
            ) {
                val lockImageResource = if (doorLock) {
                    R.drawable.lockoff
                } else {
                    R.drawable.lockon
                }
                Text(
                    modifier = Modifier.padding(start = LargeSpacing),
                    text = doorTitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .padding(end = ExtraLargeSpacing)
                        .size(25.dp),
                    painter = painterResource(id = lockImageResource),
                    contentDescription = null
                )
                AnimatedVisibility(
                    visible = isSwiped,
                    modifier = Modifier
                        .padding(end = ExtraLargeSpacing)
                        .size(25.dp)
                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

fun dpToPx(dp: Float, context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics
    )
}

@Preview
@Composable
fun DoorItemPreview() {
    MaterialTheme {
        DoorItem(
            doorTitle = stringResource(id = R.string.my_house),
            doorLock = false,
        )
    }
}