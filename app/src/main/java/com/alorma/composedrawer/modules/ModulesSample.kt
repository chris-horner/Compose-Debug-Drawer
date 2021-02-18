package com.alorma.composedrawer.modules

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.*
import java.time.LocalDate

@Composable
fun DemoActionsModule(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val dropdownItems = listOf(
        Forlayo("Item 1"),
        Forlayo("Item 2"),
        Forlayo("Item 3"),
    )

    val itemSelectorState = remember { mutableStateOf<Forlayo?>(null) }
    val dateState = remember { mutableStateOf<LocalDate>(LocalDate.now()) }

    ActionsModule(
        modifier = modifier,
        icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings") },
        title = "Actions"
    ) {
        TextAction(text = "Buttons")
        ButtonAction(text = "Button 2") {
            Toast.makeText(context, "Click Button 2", Toast.LENGTH_SHORT).show()
        }
        TextAction(text = "Switches")
        SwitchAction(text = "Switch 2", isChecked = false) { checked ->
            Toast.makeText(context, "Switch 2 change $checked", Toast.LENGTH_SHORT).show()
        }
        TextAction(text = "Selectors")
        DropdownSelectorAction(
            label = "Items",
            items = dropdownItems,
            itemFormatter = { forlayo -> forlayo.text },
            onItemSelected = { forlayo ->
                itemSelectorState.value = forlayo
                Toast.makeText(context, "Item: ${forlayo.text}", Toast.LENGTH_SHORT).show()
            }
        )
        DropdownAction(
            label = "Items",
            text = dateState.value.toString(),
        ) {
//            dialog.show()
        }
    }
}


data class Forlayo(val text: String)