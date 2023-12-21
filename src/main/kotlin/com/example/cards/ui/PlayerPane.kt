package com.example.cards.ui

import com.example.cards.domain.Card
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder

class PlayerPane(
    private val cards: List<Card>,
    private val selectedCard: Card?,
    private val enableCardSelection: Boolean,
    private val onCardSelection : (aCard: Card) -> Unit,
    private val cleanings: Int
) : JPanel() {

    init {
        isOpaque = false
        layout = GridBagLayout()

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val playerCardsPanel = JPanel()
        playerCardsPanel.isOpaque = false
        playerCardsPanel.layout = BoxLayout(playerCardsPanel, BoxLayout.X_AXIS)

        val playerAvatar = JLabel(ImageIcon(ResourceLoader.getAvatarSprite()))
        playerAvatar.border = TitledBorder("Player")
        playerCardsPanel.add(playerAvatar)

        cards.forEach {
            val image = if (it == selectedCard) {
                ResourceLoader.getCardSprite(it,ResourceLoader.CardSpriteType.SELECT)
            } else {
                ResourceLoader.getCardSprite(it,ResourceLoader.CardSpriteType.NORMAL)
            }
            val label = JLabel(ImageIcon(image))
            label.border = EmptyBorder(10, 10, 10, 10)
            if(enableCardSelection) {
                label.addMouseListener(
                    CardMouseAdapter(
                        card = it,
                        isCardSelected = selectedCard == it,
                        onClick = onCardSelection
                    )
                )
            }
            playerCardsPanel.add(label)
        }

        val box = Box.createHorizontalBox()
        val label1 = JLabel("Cleanings: $cleanings", SwingConstants.LEFT)
        box.add(label1)
        container.add(box)

        container.add(playerCardsPanel)
    }

}