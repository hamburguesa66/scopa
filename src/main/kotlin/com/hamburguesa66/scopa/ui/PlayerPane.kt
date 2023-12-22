package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import com.hamburguesa66.scopa.handlers.ResourceHandler
import com.hamburguesa66.scopa.ui.shared.AvatarPane
import com.hamburguesa66.scopa.ui.shared.BasePane
import com.hamburguesa66.scopa.ui.shared.CardMouseAdapter
import com.hamburguesa66.scopa.ui.shared.CurrentScoreBox
import javax.swing.*
import javax.swing.border.EmptyBorder

class PlayerPane(
    private val cards: List<Card>,
    private val selectedCard: Card?,
    private val enableCardSelection: Boolean,
    private val onCardSelection : (aCard: Card) -> Unit,
    private val cleanings: Int
) : BasePane() {

    init {
        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val playerCardsPanel = JPanel()
        playerCardsPanel.isOpaque = false
        playerCardsPanel.layout = BoxLayout(playerCardsPanel, BoxLayout.X_AXIS)

        playerCardsPanel.add(
            AvatarPane(
                image = ResourceHandler.getSprite(ResourceHandler.Sprite.SKELETON_AVATAR),
                name = "Player"
            )
        )

        cards.forEach {
            val image = if (it == selectedCard) {
                ResourceHandler.getCardSprite(it, ResourceHandler.CardSpriteType.SELECT)
            } else {
                ResourceHandler.getCardSprite(it, ResourceHandler.CardSpriteType.NORMAL)
            }
            val cardLabel = JLabel(ImageIcon(image))
            cardLabel.border = EmptyBorder(10, 10, 10, 10)
            if(enableCardSelection) {
                cardLabel.addMouseListener(
                    CardMouseAdapter(
                        card = it,
                        isCardSelected = selectedCard == it,
                        onClick = onCardSelection
                    )
                )
            }
            playerCardsPanel.add(cardLabel)
        }

        container.add(
            CurrentScoreBox(
                cleanings = cleanings
            )
        )

        container.add(playerCardsPanel)
    }

}