package com.alorma.drawer_base

enum class ModuleExpandedState {
    EXPANDED,
    COLLAPSED;

    operator fun not() = when (this) {
        EXPANDED -> COLLAPSED
        COLLAPSED -> EXPANDED
    }
}