package com.hamburguesa66.scopa.ui.shared

import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.SwingConstants

class CurrentScoreBox(
    private val cleanings : Int
) : Box(BoxLayout.X_AXIS) {

    init {
        add(
            JLabel("Cleanings: $cleanings", SwingConstants.LEFT)
        )
    }

}