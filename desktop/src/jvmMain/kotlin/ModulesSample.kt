package com.alorma.composedrawer

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.*
import java.time.LocalDate

@Composable
fun DemoActionsModule(modifier: Modifier = Modifier) {

    val dropdownItems = listOf(
        Forlayo("Item 1"),
        Forlayo("Item 2"),
        Forlayo("Item 3"),
    )

    val itemSelectorState = remember { mutableStateOf<Forlayo?>(null) }
    val dateState = remember { mutableStateOf<LocalDate>(LocalDate.now()) }

    ActionsModule(
        modifier = modifier,
        icon = { Icon(imageVector = Icons.Default.Settings) },
        title = "Actions"
    ) {
        TextAction(text = "Buttons")
        ButtonAction(text = "Button 2") {}
        TextAction(text = "Switches")
        SwitchAction(text = "Switch 2", isChecked = false) { checked -> }
        TextAction(text = "Selectors")
        DropdownSelectorAction(
            label = "Items",
            items = dropdownItems,
            itemFormatter = { forlayo -> forlayo.text },
            onItemSelected = { forlayo -> itemSelectorState.value = forlayo }
        )
        DropdownAction(
            label = "Items",
            text = dateState.value.toString(),
        ) {
//            dialog.show()
        }
    }
}