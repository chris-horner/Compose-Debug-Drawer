package com.alorma.drawer_modules

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.alorma.drawer_base.DebugModule
import com.alorma.drawer_base.IconType

@Composable
fun ActionsModule(
    icon: IconType,
    title: String,
    actions: @Composable () -> List<DebugDrawerAction>
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
                    val actionsSemanticModifier = Modifier.semantics {
                        testTag = "Action ${action.tag}"
                    }
                    action.build(
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(actionsSemanticModifier)
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
    open val tag: String
        get() = this::class.java.name.orEmpty()

    @Composable
    abstract fun build(modifier: Modifier)
}
