package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import com.hamburguesa66.scopa.domain.PlayerScore
import com.hamburguesa66.scopa.handlers.ResourceHandler
import com.hamburguesa66.scopa.ui.shared.AvatarPane
import com.hamburguesa66.scopa.ui.shared.BasePane
import com.hamburguesa66.scopa.ui.shared.CurrentScoreBox
import java.awt.FlowLayout
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

class CpuPane(
    private val cards: List<Card>,
    private val selectedCard: Card?,
    private val showCard: Boolean,
    private val showScore: Boolean,
    private val score: PlayerScore,
    private val avatar: ResourceHandler.Sprite
) : BasePane() {

    init {
        isOpaque = false
        layout = FlowLayout(FlowLayout.LEFT)
        border = EmptyBorder(0,if(showScore) 20 else 58,0,0)

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val cpuHandPanel = JPanel()
        cpuHandPanel.isOpaque = false
        cpuHandPanel.layout = BoxLayout(cpuHandPanel, BoxLayout.X_AXIS)

        cpuHandPanel.add(
            AvatarPane(
                image = ResourceHandler.getSprite(avatar),
                name = "CPU"
            )
        )

        cards.forEach {
            val image = if (showCard && it == selectedCard) {
                ResourceHandler.getCardSprite(it, ResourceHandler.CardSpriteType.SELECT)
            } else {
                ResourceHandler.getSprite(ResourceHandler.Sprite.FACE_DOWN_CARD)
            }
            val label = JLabel(ImageIcon(image))
            label.border = EmptyBorder(10, 10, 10, 10)
            cpuHandPanel.add(label)
        }

        (1..(3 - cards.size)).forEach { _ ->
            val aux = JLabel(ImageIcon(ResourceHandler.getSprite(ResourceHandler.Sprite.BLANK_CARD)))
            aux.border = EmptyBorder(10, 10, 10, 10)
            cpuHandPanel.add(aux)
        }

        container.add(cpuHandPanel)

        if(showScore) {
            container.add(
                CurrentScoreBox(
                    score = score,
                    padding = EmptyBorder(0,40,0,0)
                )
            )
        }
    }

}