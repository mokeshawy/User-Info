package com.tawuniya.userinfo.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tawuniya.userinfo.R
import com.tawuniya.userinfo.composable.GeneralError
import com.tawuniya.userinfo.composable.MainTopBar
import com.tawuniya.userinfo.core.extensions.opeDilNumber
import com.tawuniya.userinfo.core.extensions.openGoogleMaps
import com.tawuniya.userinfo.core.extensions.openUrl
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel
import com.tawuniya.userinfo.features.home.domain.viewmodel.HomeViewModel
import com.tawuniya.userinfo.ui.theme.natural_light
import com.tawuniya.userinfo.ui.theme.natural_normal
import com.tawuniya.userinfo.ui.theme.secondary_normal
import com.tawuniya.userinfo.ui.theme.white_normal
import com.tawuniya.userinfo.ui.theme.yellow_normal


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val userInfoUiState = viewModel.uiState

    MainTopBar(
        isShowRightIcon = false,
        onRefresh = { viewModel.sendGetUserInfoIntent() },
        isRefreshing = userInfoUiState.isLoading,
        title = R.string.home
    ) {

        if (userInfoUiState.error != null) {
            GeneralError(message = userInfoUiState.error.logMessage, onRefresh = {
                viewModel.sendGetUserInfoIntent()
            })
        }

        if (userInfoUiState.userInfoUiModelList?.isNotEmpty() == true) {
            HomeContent(userInfoUiModel = userInfoUiState.userInfoUiModelList)
        }
    }
}


@Composable
fun HomeContent(userInfoUiModel: List<UserInfoUiModel>) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(userInfoUiModel, key = { it.id }) { item ->
            UserInfoItem(
                email = item.email,
                id = item.id,
                name = item.name,
                phone = item.phone,
                username = item.username,
                website = item.website,
                lat = item.address.geofence.lat.toDouble(),
                lng = item.address.geofence.lng.toDouble(),
                isFavorite = false, //TODO WILL BE HANDLE WITH ROOM IMPLEMENTATION
                onItemClicked = {}
            )
        }
    }
}


@Composable
fun UserInfoItem(
    email: String,
    id: Int,
    name: String,
    phone: String,
    username: String,
    website: String,
    lat: Double,
    lng: Double,
    isFavorite: Boolean,
    onItemClicked: () -> Unit
) {

    val context = LocalContext.current
    var isFav by remember { mutableStateOf(isFavorite) }

    Surface(
        modifier = Modifier
            .clickable { onItemClicked() }
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = natural_light,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = white_normal)
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .background(color = white_normal)
                .padding(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.userID, id),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { isFav = !isFav },
                        painter = painterResource(id = if (isFav) R.drawable.ic_vector_archived else R.drawable.ic_vector_add_archive),
                        tint = if (isFav) yellow_normal else natural_normal,
                        contentDescription = "Favorite"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { context.openGoogleMaps(latitude = lat, longitude = lng) },
                        painter = painterResource(R.drawable.ic_vector_location),
                        tint = secondary_normal,
                        contentDescription = "Location"
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                NameAndPhoneContent(
                    name = name,
                    phone = phone,
                    onPhoneNumberClicked = { phone.opeDilNumber(context = context) })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                UserNameAndWebsiteContent(
                    userName = username,
                    website = website,
                    onWebSiteClicked = { website.openUrl(context = context) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.email),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    modifier = Modifier.clickable { },
                    text = email,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun NameAndPhoneContent(
    name: String,
    phone: String,
    onPhoneNumberClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = stringResource(R.string.name),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = name,
            fontSize = 16.sp
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = stringResource(R.string.phoneNumber),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            modifier = Modifier.clickable { onPhoneNumberClicked() },
            text = phone,
            fontSize = 16.sp
        )
    }
}


@Composable
fun UserNameAndWebsiteContent(
    userName: String,
    website: String,
    onWebSiteClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = stringResource(R.string.userName),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = userName,
            fontSize = 16.sp
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = stringResource(R.string.webSite),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            modifier = Modifier.clickable { onWebSiteClicked() },
            text = website,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserinfoItemPreview() {
    UserInfoItem(
        email = "mokeshawy@gamil.com",
        id = 1,
        name = "Mohamed Keshawy",
        phone = "+201007169654",
        username = "M.Keshawy",
        website = "https://github.com/mokeshawy",
        lat = 30.3848,
        lng = 40.2222,
        isFavorite = true,
        onItemClicked = {}
    )
}