package com.tawuniya.userinfo.composable

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tawuniya.userinfo.ui.theme.secondary_light
import com.tawuniya.userinfo.ui.theme.secondary_normal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.PullToRefreshIndicator(
    isRefreshing: Boolean,
    state: PullToRefreshState,
    modifier: Modifier = Modifier,
    containerColor: Color = secondary_light,
    color: Color = secondary_normal,
) {
    Indicator(
        modifier = modifier.align(Alignment.TopCenter),
        isRefreshing = isRefreshing,
        state = state,
        containerColor = containerColor,
        color = color
    )
}