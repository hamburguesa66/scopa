package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import com.hamburguesa66.scopa.domain.PlayerScore
import com.hamburguesa66.scopa.handlers.ResourceHandler
import com.hamburguesa66.scopa.ui.shared.AvatarPane
import com.hamburguesa66.scopa.ui.shared.BasePane
import com.hamburguesa66.scopa.ui.shared.CardMouseAdapter
import com.hamburguesa66.scopa.ui.shared.CurrentScoreBox
import java.awt.FlowLayout
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.EtchedBorder

class PlayerPane(
    private val cards: List<Card>,
    private val selectedCard: Card?,
    private val enableCardSelection: Boolean,
    private val onCardSelection : (aCard: Card) -> Unit,
    private val showScore: Boolean,
    private val score: PlayerScore,
) : BasePane() {

    init {
        isOpaque = false
        layout = FlowLayout(FlowLayout.RIGHT)
        border = EmptyBorder(0,0,0,if(showScore) 20 else 57)

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val playerCardsPanel = JPanel()
        playerCardsPanel.isOpaque = false
        playerCardsPanel.layout = BoxLayout(playerCardsPanel, BoxLayout.X_AXIS)

        (1..(3 - cards.size)).forEach { _ ->
            val aux = JLabel(ImageIcon(ResourceHandler.getSprite(ResourceHandler.Sprite.BLANK_CARD)))
            aux.border = EmptyBorder(10, 10, 10, 10)
            playerCardsPanel.add(aux)
        }

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

        if(showScore) {
            container.add(
                CurrentScoreBox(
                    score = score,
                    padding = EmptyBorder(0,0,0,40)
                )
            )
        }

        container.add(playerCardsPanel)

        playerCardsPanel.add(
            AvatarPane(
                image = ResourceHandler.getSprite(ResourceHandler.Sprite.MIKE_AVATAR),
                name = "Player",
                useAltNamePosition = true
            )
        )
    }

}