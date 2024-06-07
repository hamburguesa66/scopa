package com.hamburguesa66.scopa.ui.system

import javax.swing.JCheckBox

class CheckboxInput(
    private val label: String,
    private val defaultValue: Boolean,
    private val callback: (Boolean) -> Unit
) : JCheckBox(label, defaultValue) {

    init {
        addItemListener {
            callback(it.stateChange == 1)
        }
    }

}