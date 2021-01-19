package com.alorma.composedrawer.modules

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import com.alorma.composedrawer.R
import com.alorma.drawer_base.DebugModule
import com.alorma.drawer_base.IconType
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.buttonAction
import com.alorma.drawer_modules.actions.dropdownSelectorAction
import com.alorma.drawer_modules.actions.switchAction
import com.alorma.drawer_modules.actions.textAction

@Composable
fun demoActionsModule(): DebugModule {
    val context = AmbientContext.current
    return ActionsModule(
        icon = IconType.Vector(R.drawable.ic_settings),
        title = "Actions",
        actions = {
            val dropdownItems = listOf(
                Forlayo("Item 1"),
                Forlayo("Item 2"),
                Forlayo("Item 3"),
            )
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
                    items = dropdownItems,
                    defaultValue = dropdownItems.first(),
                    itemFormatter = { forlayo -> forlayo.text },
                    onItemSelected = { forlayo ->
                        Toast.makeText(context, "Item: ${forlayo.text}", Toast.LENGTH_SHORT).show()
                    }
                ),
            )
        })
}

data class Forlayo(val text: String)