package com.alorma.composedrawer.modules

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AmbientContext
import com.alorma.composedrawer.R
import com.alorma.drawer_base.DebugModule
import com.alorma.drawer_base.IconType
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.datetimepicker
import java.time.LocalDateTime

@Composable
fun demoActionsModule(dateState: MutableState<LocalDateTime>): DebugModule {
    val context = AmbientContext.current

    val dropdownItems = listOf(
        Forlayo("Item 1"),
        Forlayo("Item 2"),
        Forlayo("Item 3"),
    )

    val itemSelectorState = remember { mutableStateOf(dropdownItems.first()) }

    return ActionsModule(
        icon = IconType.Vector(R.drawable.ic_settings),
        title = "Actions",
        actions = {
            val dialog = MaterialDialog()
            dialog.build {
                datetimepicker(title = "Select") { localDateTime ->
                    dateState.value = localDateTime
                }
            }

            listOf(
                textAction(text = "Buttons"),
                buttonAction("Button 1") {
                    Toast.makeText(context, "Click Button 1", Toast.LENGTH_SHORT).show()
                },
                buttonAction("Button 2") {
                    Toast.makeText(context, "Click Button 2", Toast.LENGTH_SHORT).show()
                },
                textAction(text = "Switches"),
                switchAction("Switch 1", true) { checked ->
                    Toast.makeText(context, "Switch 1 change $checked", Toast.LENGTH_SHORT).show()
                },
                switchAction("Switch 2", false) { checked ->
                    Toast.makeText(context, "Switch 2 change $checked", Toast.LENGTH_SHORT).show()
                },
                textAction(text = "Selectors"),
                dropdownSelectorAction(
                    label = "Items",
                    items = dropdownItems,
                    defaultValue = itemSelectorState.value,
                    itemFormatter = { forlayo -> forlayo.text },
                    onItemSelected = { forlayo ->
                        itemSelectorState.value = forlayo
                        Toast.makeText(context, "Item: ${forlayo.text}", Toast.LENGTH_SHORT).show()
                    }
                )
            )
        })
}