package com.alorma.composedrawer.modules

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AmbientContext
import com.alorma.composedrawer.R
import com.alorma.drawer_base.IconType
import com.alorma.drawer_modules.actions.*
import com.alorma.drawer_modules.actionsModule

@Composable
fun DemoActionsModule() {
    val context = AmbientContext.current

    val dropdownItems = listOf(
        Forlayo("Item 1"),
        Forlayo("Item 2"),
        Forlayo("Item 3"),
    )

    val itemSelectorState = remember { mutableStateOf<Forlayo?>(null) }

    actionsModule(
        icon = IconType.Vector(R.drawable.ic_settings),
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
    }
}