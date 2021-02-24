package com.alorma.drawer_modules

import android.content.Context
import android.os.Build
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun BuildModule(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    fun obtainBuildInfo(context: Context): List<Pair<String, String>> {
        val info = context.packageManager.getPackageInfo(context.packageName, 0)

        val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info.longVersionCode.toString()
        } else {
            info.versionCode.toString()
        }

        val infoVersion = "Version" to versionCode
        val infoName = "Name" to info.versionName
        val infoPackage = "Package" to info.packageName

        return listOf(
            infoVersion,
            infoName,
            infoPackage
        )
    }

    fun obtainDebugInfo(): List<Pair<String, String>> {
        val infoVersion = "Version" to "11141"
        val infoName = "Name" to "1.1.4"
        val infoPackage = "Package" to "com.alorma"

        return listOf(
            infoVersion,
            infoName,
            infoPackage
        )
    }

    val items = try {
        obtainBuildInfo(context)
    } catch (e: NullPointerException) {
        obtainDebugInfo()
    }

    val title = "Build information"

    InfoModule(
        modifier = modifier,
        icon = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Build icon",
            )
        },
        title = title,
        items = items
    )
}