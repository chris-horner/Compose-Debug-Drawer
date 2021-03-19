package com.alorma.drawer_ui_modules.design

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridOff
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.drawer_base.DebugDrawerModule
import com.alorma.drawer_modules.actions.SwitchAction

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DesignModule(
    modifier: Modifier = Modifier,
    config: DebugGridStateConfig,
    onChange: (DebugGridStateConfig) -> Unit,
) {

    DebugDrawerModule(
        modifier = modifier,
        icon = {
            val icon = if (config.isEnabled) {
                Icons.Default.GridOn
            } else {
                Icons.Default.GridOff
            }
            Icon(
                imageVector = icon,
                contentDescription = "Design icon",
            )
        },
        title = "Design"
    ) {
        val text = if (config.isEnabled) {
            "Grid enabled"
        } else {
            "Grid disabled"
        }
        SwitchAction(text = text, isChecked = config.isEnabled) { enabled ->
            val result = config.copy(isEnabled = enabled)
            onChange(result)
        }
        AnimatedVisibility(visible = config.isEnabled) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
            ) {
                Text(
                    text = "Alpha",
                    style = MaterialTheme.typography.caption
                )
                Slider(
                    value = config.alpha,
                    onValueChange = { alpha ->
                        val result = config.copy(alpha = alpha)
                        onChange(result)
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colors.secondary
                    ),
                )
            }
        }
    }
}