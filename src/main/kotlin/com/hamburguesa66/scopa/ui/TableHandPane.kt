package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import com.hamburguesa66.scopa.handlers.ResourceHandler
import com.hamburguesa66.scopa.ui.shared.BasePane
import com.hamburguesa66.scopa.ui.shared.CardMouseAdapter
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.EtchedBorder

class TableHandPane(
    private val deck: List<Card>,
    private val cards: List<Card>,
    private val selectedCards: List<Card>,
    private val submitButtonText: String,
    private val enableSubmitButton: Boolean,
    private val enableCardSelection: Boolean,
    private val onCardSelection: (aCard: Card) -> Unit,
    private val onSubmit: () -> Unit
) : BasePane() {

    init {
        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val tablePanel = JPanel()
        tablePanel.isOpaque = false
        tablePanel.layout = BoxLayout(tablePanel, BoxLayout.X_AXIS)
        tablePanel.border = EtchedBorder()

        val deckImage = JLabel(ImageIcon(ResourceHandler.getSprite(ResourceHandler.Sprite.DECK)))
        deckImage.border = EmptyBorder(10, 10, 10, 10)
        if(deck.isNotEmpty()) {
            tablePanel.add(deckImage)
        }

        cards.forEach {
            val image = if (selectedCards.contains(it) ) {
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
                        isCardSelected = selectedCards.contains(it),
                        onClick = onCardSelection
                    )
                )
            }

            tablePanel.add(cardLabel)
        }

        container.add(tablePanel)
        container.add(createSubmitButtonBox())
    }

    private fun createSubmitButtonBox() : Box {
        val submitButton = JButton(submitButtonText)
        submitButton.isEnabled = enableSubmitButton
        submitButton.setFocusPainted(false)
        submitButton.addActionListener { _ ->
            run {
                onSubmit()
            }
        }

        val box = Box.createHorizontalBox()
        box.border = EmptyBorder(10, 10, 10, 10)
        box.add(submitButton)

        return box
    }

}