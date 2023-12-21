package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
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
            ImageIcon(ResourceLoader.getCardSprite(card, ResourceLoader.CardSpriteType.HOVER_ALT))
        } else {
            ImageIcon(ResourceLoader.getCardSprite(card,ResourceLoader.CardSpriteType.HOVER))
        }
    }

    override fun mouseExited(e: MouseEvent?) {
        getComponentAsJLabel(e).icon = if (isCardSelected ) {
            ImageIcon(ResourceLoader.getCardSprite(card,ResourceLoader.CardSpriteType.SELECT))
        } else {
            ImageIcon(ResourceLoader.getCardSprite(card,ResourceLoader.CardSpriteType.NORMAL))
        }
    }

    private fun getComponentAsJLabel(e: MouseEvent?) : JLabel = e?.component as JLabel
}