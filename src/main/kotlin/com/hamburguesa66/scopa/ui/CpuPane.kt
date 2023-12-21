package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import com.hamburguesa66.scopa.ui.shared.AvatarPane
import com.hamburguesa66.scopa.ui.shared.CurrentScoreBox
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

class CpuPane(
    private val cards: List<Card>,
    private val selectedCard: Card?,
    private val showCard: Boolean,
    private val cleanings: Int
) : JPanel() {

    init {
        isOpaque = false
        layout = GridBagLayout()

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val cpuHandPanel = JPanel()
        cpuHandPanel.isOpaque = false
        cpuHandPanel.layout = BoxLayout(cpuHandPanel, BoxLayout.X_AXIS)

        cpuHandPanel.add(
            AvatarPane(
                image = ResourceLoader.getAvatarSprite(),
                name = "CPU"
            )
        )

        cards.forEach {
            val image = if (showCard && it == selectedCard) {
                ResourceLoader.getCardSprite(it,ResourceLoader.CardSpriteType.SELECT)
            } else {
                ResourceLoader.getFaceDownCardSprite()
            }
            val label = JLabel(ImageIcon(image))
            label.border = EmptyBorder(10, 10, 10, 10)
            cpuHandPanel.add(label)
        }

        container.add(cpuHandPanel)
        container.add(
            CurrentScoreBox(
                cleanings = cleanings
            )
        )
    }

}