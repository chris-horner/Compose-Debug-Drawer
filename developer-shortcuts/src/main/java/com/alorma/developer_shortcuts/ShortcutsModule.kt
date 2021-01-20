package com.alorma.developer_shortcuts

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import com.alorma.drawer_base.DebugDrawerModule
import com.alorma.drawer_modules.actions.ButtonAction
import com.alorma.drawer_modules.actions.SwitchAction
import com.alorma.drawer_modules.actions.TextAction
import com.chuckerteam.chucker.api.Chucker
import leakcanary.AppWatcher
import leakcanary.LeakCanary

@Composable
fun ShortcutsModule(modifier: Modifier = Modifier) {
    val context = AmbientContext.current

    DebugDrawerModule(
        modifier = modifier,
        icon = {
            Icon(imageVector = Icons.Default.Extension)
        },
        title = "Developer Shortcuts"
    ) {

        ButtonAction(text = "Network logs", onClick = {
            context.startActivity(Chucker.getLaunchIntent(context))
        }).takeIf {
            classExists("com.chuckerteam.chucker.api.Chucker")
        }

        ButtonAction(text = "Notification channels", onClick = {
            val intent = Intent(
                Settings.ACTION_APP_NOTIFICATION_SETTINGS
            ).putExtra(
                Settings.EXTRA_APP_PACKAGE, context.packageName
            )
            context.startActivity(intent)
        }).takeIf { Build.VERSION.SDK_INT >= Build.VERSION_CODES.O }

        TextAction(text = "Leak Canary").takeIf {
            classExists("leakcanary.LeakCanary")
        }

        SwitchAction(text = "Enable", isChecked = false, onChange = { enable ->
            AppWatcher.config = AppWatcher.config.copy(enabled = enable)
            LeakCanary.config = LeakCanary.config.copy(dumpHeap = enable)
            LeakCanary.showLeakDisplayActivityLauncherIcon(enable)
        }).takeIf {
            classExists("leakcanary.LeakCanary")
        }

        ButtonAction(text = "Reports", onClick = {
            context.startActivity(LeakCanary.newLeakDisplayActivityIntent())
        }).takeIf {
            classExists("leakcanary.LeakCanary")
        }

        ButtonAction(text = "Dump", onClick = {
            LeakCanary.dumpHeap()
        }).takeIf {
            classExists("leakcanary.LeakCanary")
        }
    }
}

private fun classExists(className: String): Boolean {
    return try {
        Class.forName(className)
        true
    } catch (ignored: ClassNotFoundException) {
        false
    }
}
