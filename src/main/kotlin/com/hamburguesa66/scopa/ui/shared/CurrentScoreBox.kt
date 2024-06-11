package com.hamburguesa66.scopa.ui.shared

import com.hamburguesa66.scopa.domain.PlayerScore
import com.hamburguesa66.scopa.handlers.ResourceHandler
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingConstants
import javax.swing.border.EmptyBorder

class CurrentScoreBox(
    private val score: PlayerScore,
    private val padding: EmptyBorder,
) : Box(BoxLayout.X_AXIS) {
    init {
        border = padding
        add(
            JLabel("Cleanings: ${score.cleanings}")
        )
        add(
            JLabel(" | 7 Münzen: ${if(score.joker > 0) "Yes" else "No" }")
        )
        add(
            JLabel(" | Sevens: ${score.sevens}")
        )
        add(
            JLabel(" | Golds: ${score.golds}")
        )
        add(
            JLabel(" | Cards: ${score.cards}")
        )
    }

}