package com.alorma.drawer_base

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerDivider() {
    Spacer(modifier = Modifier.preferredHeight(4.dp))
    Divider(
        color = MaterialTheme.colors.onSurface.compositeOverSurface(0.12f)
    )
    Spacer(modifier = Modifier.preferredHeight(4.dp))
}