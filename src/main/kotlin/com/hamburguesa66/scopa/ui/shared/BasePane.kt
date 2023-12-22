package com.hamburguesa66.scopa.ui.shared

import java.awt.GridBagLayout
import javax.swing.JPanel

open class BasePane : JPanel(GridBagLayout()) {
    init {
        isOpaque = false
    }
}