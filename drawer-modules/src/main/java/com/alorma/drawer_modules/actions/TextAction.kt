package com.alorma.drawer_modules.actions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.drawer_modules.DebugDrawerAction

@Composable
fun textAction(
    text: String,
    tag: String? = null,
    extraModifier: Modifier = Modifier,
) = object : DebugDrawerAction() {

    override val tag: String
        get() = tag ?: super.tag

    @Composable
    override fun build(modifier: Modifier) {
        Row(
            modifier = modifier
                .then(Modifier.preferredHeight(36.dp))
                .then(extraModifier),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                color = MaterialTheme.colors.secondary,
                text = text
            )
        }
    }
}