package com.hamburguesa66.scopa.ui.shared

import com.hamburguesa66.scopa.domain.Card
import com.hamburguesa66.scopa.handlers.ResourceHandler
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.ImageIcon
import javax.swing.JLabel

class CardMouseAdapter(
    private val card: Card,
    private val isCardSelected: Boolean,
    private val onClick : (aCard: Card) -> Unit
) : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) = onClick(card)

    override fun mouseEntered(e: MouseEvent?) {
        getComponentAsJLabel(e).icon = if (isCardSelected) {
            ImageIcon(ResourceHandler.getCardSprite(card, ResourceHandler.CardSpriteType.HOVER_ALT))
        } else {
            ImageIcon(ResourceHandler.getCardSprite(card, ResourceHandler.CardSpriteType.HOVER))
        }
    }

    override fun mouseExited(e: MouseEvent?) {
        getComponentAsJLabel(e).icon = if (isCardSelected ) {
            ImageIcon(ResourceHandler.getCardSprite(card, ResourceHandler.CardSpriteType.SELECT))
        } else {
            ImageIcon(ResourceHandler.getCardSprite(card, ResourceHandler.CardSpriteType.NORMAL))
        }
    }

    private fun getComponentAsJLabel(e: MouseEvent?) : JLabel = e?.component as JLabel
}