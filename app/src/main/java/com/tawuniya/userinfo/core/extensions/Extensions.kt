package com.tawuniya.userinfo.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.tawuniya.userinfo.BuildConfig
import com.tawuniya.userinfo.R
import com.tawuniya.userinfo.core.error.AppError
import com.tawuniya.userinfo.core.error.FiledToOpenGoogleMapsException
import com.tawuniya.userinfo.core.error.FiledToOpenWhatsAppException
import com.tawuniya.userinfo.core.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


fun Exception.getValidationError() = AppError.I(exception = this)


fun Context.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun String.openUrl(context: Context) {
    val fileUri = Uri.parse(this)
    val extension = fileUri.toString().substringAfterLast('.', "")
    val mimeType = getMimeTypeFromExtension(extension)
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(fileUri, mimeType)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        AppError.E(exception = e, "Open file with action view error")
    }
}

private fun getMimeTypeFromExtension(extension: String): String {
    return when (extension.lowercase(Locale.getDefault())) {
        "jpg", "jpeg", "png" -> "image/*"
        "pdf" -> "application/pdf"
        "docx", "doc" -> "application/*"
        else -> "*/*"
    }
}


fun String.opeDilNumber(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.setData(Uri.parse("tel:$this"))
    context.startActivity(intent)
}


fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        AppError.E(e, logMessage = "Send mail cannot be send :$e")
    } catch (e: Exception) {
        AppError.E(e, logMessage = "Send mail cannot be send :$e")
    }
}


fun String.getDateOnly(): String {
    val parsedDate = LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    return parsedDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
}


fun Context.openAppSetting() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri: Uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
    intent.data = uri
    startActivity(intent)
}


fun <T : Any> LazyPagingItems<T>.loadToFirstItem(onError: () -> Unit, onLoading: () -> Unit) =
    when (this.loadState.refresh) {
        is LoadState.Error -> onError()

        is LoadState.Loading -> onLoading()
        is LoadState.NotLoading -> {}
    }


fun <T : Any> LazyPagingItems<T>.loadToMoreItem(onError: () -> Unit, onLoading: () -> Unit) =
    when (this.loadState.append) {
        is LoadState.Error -> onError()

        is LoadState.Loading -> onLoading()
        is LoadState.NotLoading -> {}
    }


@SuppressLint("SimpleDateFormat")
fun Int.getMonthByNumber(): String {
    val calendar = Calendar.getInstance()
    val monthDate = SimpleDateFormat("MMMM")
    calendar[Calendar.MONTH] = this - 1
    return monthDate.format(calendar.time)
}


fun Float.getHoursMinutesSeconds(): Triple<Int, Int, Int> {
    val hours = this.toInt()
    val minutesPart = (this - hours) * 60
    val minutes = minutesPart.toInt()
    val seconds = ((minutesPart - minutes) * 60).toInt()
    return Triple(hours, minutes, seconds)
}


fun Context.restartActivity(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
}


fun String.formatIsoDate(outPutPattern: String = "dd MMM, yyyy 'at' hh:mm a"): String {
    val parsedDate = LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    val outputFormatter = DateTimeFormatter.ofPattern(outPutPattern)
    return parsedDate.format(outputFormatter)
}

fun Context.openGPSSettings() {
    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    startActivity(intent)
}


@SuppressLint("DefaultLocale")
fun Long.formatTimeFromSeconds(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds, Locale.ENGLISH)
}


fun Context.openWhatsApp(phoneNumber: String) {
    val whatsAppPackageName = "com.whatsapp"
    val isWhatsAppInstalled = isAppInstalled(whatsAppPackageName)
    if (!isWhatsAppInstalled) {
        openWhatsAppOnPlayStore()
        return
    }
    try {
        val uri = "https://api.whatsapp.com/send?phone=$phoneNumber"
        actionViewIntent(uri = uri, packageName = whatsAppPackageName)
    } catch (e: Exception) {
        AppError.E(FiledToOpenWhatsAppException(), "Failed to open WhatsApp")
        showToast(R.string.failedOpenWhatsApp)
    }
}


private fun Context.openWhatsAppOnPlayStore() {
    try {
        val uri = "market://details?id=com.whatsapp"
        actionViewIntent(uri = uri)
    } catch (e: Exception) {
        val uri = "https://play.google.com/store/apps/details?id=com.whatsapp"
        actionViewIntent(uri = uri)
    }
}


fun Context.openGoogleMaps(latitude: Double, longitude: Double) {
    val isGoogleMapInstall = isAppInstalled("com.google.android.apps.maps")
    if (!isGoogleMapInstall) {
        openGooglePlayToInstallGoogleMaps()
        return
    }
    try {
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude"
        val packageName = "com.google.android.apps.maps"
        actionViewIntent(uri = uri, packageName = packageName)
    } catch (e: Exception) {
        AppError.E(FiledToOpenGoogleMapsException(), "Failed to open Google Maps")
        showToast(R.string.failedOpenGoogleMaps)
    }
}


private fun Context.openGooglePlayToInstallGoogleMaps() {
    try {
        val uri = "market://details?id=com.google.android.apps.maps"
        actionViewIntent(uri = uri)
    } catch (e: Exception) {
        val uri = "https://play.google.com/store/apps/details?id=com.google.android.apps.maps"
        actionViewIntent(uri = uri)
    }
}


fun Context.actionViewIntent(uri: String, packageName: String? = null) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    packageName?.let { intent.setPackage(it) }
    startActivity(intent)
}


private fun Context.isAppInstalled(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}


fun ViewModel.viewModelScope(block: suspend () -> Unit) = this.viewModelScope.launch {
    block()
}


suspend inline fun <T> Flow<State<T>>.collectOnFlowState(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (AppError) -> Unit,
    crossinline onSuccess: suspend (Any) -> Unit,
) = collect {
    onLoading()
    when (it) {
        is State.Error -> onError(it.error)
        is State.Loading -> {}
        is State.Success -> onSuccess(it.data ?: return@collect)
    }
}


inline fun <reified T> List<T>.encodeListToString(): String {
    val json = Json.encodeToString(this)
    return Uri.encode(json)
}

inline fun <reified T> String.decodeFromString(): T {
    return Json.decodeFromString(this)
}

