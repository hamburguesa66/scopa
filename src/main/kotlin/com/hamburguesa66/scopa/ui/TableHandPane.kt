package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.domain.Card
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.EtchedBorder

class TableHandPane(
    private val cards: List<Card>,
    private val selectedCards: List<Card>,
    private val submitButtonText: String,
    private val enableSubmitButton: Boolean,
    private val enableCardSelection: Boolean,
    private val onCardSelection: (aCard: Card) -> Unit,
    private val onSubmit: () -> Unit
) : JPanel() {

    init {
        isOpaque = false
        layout = GridBagLayout()

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        add(container)

        val tablePanel = JPanel()
        tablePanel.isOpaque = false
        tablePanel.layout = BoxLayout(tablePanel, BoxLayout.X_AXIS)
        tablePanel.border = EtchedBorder()

        val deckImage = JLabel(ImageIcon(ResourceLoader.getDeckSprite()))
        deckImage.border = EmptyBorder(10, 10, 10, 10)
        tablePanel.add(deckImage)

        cards.forEach {
            val image = if (selectedCards.contains(it) ) {
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
                        isCardSelected = selectedCards.contains(it),
                        onClick = onCardSelection
                    )
                )
            }
            tablePanel.add(label)
        }

        container.add(tablePanel)

        val box = Box.createHorizontalBox()
        val label1 = JButton(submitButtonText)
        label1.isEnabled = enableSubmitButton
        label1.setFocusPainted(false)
        label1.addActionListener { _ ->
            run {
                onSubmit()
            }
        }
        box.border = EmptyBorder(10, 10, 10, 10)
        box.add(label1)
        container.add(box)
    }

}