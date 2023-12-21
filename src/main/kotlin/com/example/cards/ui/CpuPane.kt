package com.example.cards.ui

import com.example.cards.domain.Card
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder


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

        val cpuAvatar = JLabel(ImageIcon(ResourceLoader.getAvatarSprite()))
        cpuAvatar.border = TitledBorder("CPU")
        cpuHandPanel.add(cpuAvatar)

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

        val box = Box.createHorizontalBox()
        val label1 = JLabel("Cleanings: $cleanings", SwingConstants.LEFT)
        box.add(label1)
        container.add(box)
    }

}