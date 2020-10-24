package com.alorma.drawer_modules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alorma.drawer_base.DebugModule
import com.alorma.drawer_base.DrawerColors
import com.alorma.drawer_base.IconType

@Composable
fun ActionsModule(
    icon: IconType,
    title: String,
    actions: @Composable ColumnScope.() -> List<DebugDrawerAction>
) = object : DebugModule {

    override val icon: IconType = icon
    override val title: String = title

    @Composable
    override fun build() {
        Column {
            val actionItems = actions()
            actionItems.forEachIndexed { index, action ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    action.build(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                if (index < actionItems.size - 1) {
                    Spacer(modifier = Modifier.preferredHeight(8.dp))
                }
            }
        }
    }
}

abstract class DebugDrawerAction {
    @Composable
    abstract fun build(modifier: Modifier)
}

@Composable
fun ButtonAction(
    text: String,
    extraModifier: Modifier = Modifier,
    onClick: () -> Unit
) = object : DebugDrawerAction() {

    @Composable
    override fun build(modifier: Modifier) {
        Button(
            modifier = modifier.then(extraModifier),
            backgroundColor = DrawerColors.current.primary,
            contentColor = DrawerColors.current.onPrimary,
            onClick = onClick,
            content = { Text(text) }
        )
    }
}

@Composable
fun SwitchAction(
    text: String,
    isChecked: Boolean,
    extraModifier: Modifier = Modifier.border(
        border = BorderStroke(width = 1.dp, color = DrawerColors.current.primary),
        shape = MaterialTheme.shapes.medium
    ),
    onChange: (checked: Boolean) -> Unit,
) = object : DebugDrawerAction() {

    @Composable
    override fun build(modifier: Modifier) {
        fun onSwitchChange(checkedState: MutableState<Boolean>, checked: Boolean) {
            checkedState.value = checked
            onChange(checkedState.value)
        }

        val checkedState: MutableState<Boolean> = remember { mutableStateOf(isChecked) }

        Row(
            modifier = modifier.preferredHeight(36.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .then(extraModifier)
                .clickable(onClick = {
                    onSwitchChange(
                        checkedState = checkedState,
                        checked = !checkedState.value
                    )
                })
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Start,
            )
            Spacer(
                modifier = Modifier.fillMaxHeight().weight(1f)
            )
            Switch(
                color = DrawerColors.current.primary,
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    onSwitchChange(
                        checkedState = checkedState,
                        checked = checked
                    )
                },
            )
        }
    }

}
