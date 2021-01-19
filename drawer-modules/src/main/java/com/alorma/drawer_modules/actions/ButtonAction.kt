package com.alorma.drawer_modules.actions

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.drawer_modules.DebugDrawerAction

@Composable
fun buttonAction(
    text: String,
    tag: String? = null,
    extraModifier: Modifier = Modifier,
    onClick: () -> Unit
) = object : DebugDrawerAction() {

    override val tag: String
        get() = tag ?: super.tag

    @Composable
    override fun build(modifier: Modifier) {
        Button(
            modifier = modifier.then(extraModifier),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            ),
            onClick = onClick,
            content = { Text(text) }
        )
    }
}