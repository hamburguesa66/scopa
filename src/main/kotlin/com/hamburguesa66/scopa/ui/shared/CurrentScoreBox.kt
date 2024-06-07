package com.hamburguesa66.scopa.ui.shared

import com.hamburguesa66.scopa.domain.PlayerScore
import com.hamburguesa66.scopa.handlers.ResourceHandler
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingConstants

class CurrentScoreBox(
    private val score: PlayerScore
) : Box(BoxLayout.X_AXIS) {
    init {
        add(
            JLabel("Cleanings: ${score.cleanings}", SwingConstants.LEFT)
        )
        add(
            JLabel(" | 7 Münzen: ${if(score.joker > 0) "Yes" else "No" }", SwingConstants.LEFT)
        )
        add(
            JLabel(" | Sevens: ${score.sevens}", SwingConstants.LEFT)
        )
        add(
            JLabel(" | Golds: ${score.golds}", SwingConstants.LEFT)
        )
        add(
            JLabel(" | Cards: ${score.cards}", SwingConstants.LEFT)
        )
    }

}