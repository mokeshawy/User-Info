package com.tawuniya.userinfo.composable

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.tawuniya.userinfo.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    isShowLeftIcon: Boolean = false,
    isShowRightIcon : Boolean = false,
    isRefreshing: Boolean = false,
    @StringRes
    title: Int = R.string.app_name,
    @DrawableRes
    leftIcon : Int = R.drawable.ic_vector_arrow_back,
    @DrawableRes
    rightIcon : Int =  R.drawable.ic_vector_menu,
    contentAlignment: Alignment = Alignment.TopStart,
    onRefresh: () -> Unit = {},
    onRightIconClicked: () -> Unit = {},
    onBackHandler: () -> Unit = {},
    onLeftIconClicked: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val refreshState = rememberPullToRefreshState()

    BackHandler { onBackHandler() }
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopAppBar(
                title = stringResource(id = title), navigationIcon = {
                    if (isShowLeftIcon) {
                        IconButton(onClick = { onLeftIconClicked() }) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(id = leftIcon),
                                    contentDescription = "Menu icon",
                                )
                            }
                        }
                    }
                }, actions = {
                   if(isShowRightIcon){
                       IconButton(onClick = dropUnlessResumed(block = onRightIconClicked)) {
                           IconWrapper(size = 24.dp) {
                               Icon(
                                   painter = painterResource(id =rightIcon),
                                   contentDescription = "Profile",
                                   modifier = Modifier.size(20.dp)
                               )
                           }
                       }
                   }
                }, scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullToRefresh(
                    state = refreshState, isRefreshing = isRefreshing, onRefresh = onRefresh
                ), contentAlignment = contentAlignment
        ) {

            content()

            PullToRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = refreshState
            )
        }
    }
}