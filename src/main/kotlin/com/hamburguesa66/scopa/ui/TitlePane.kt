package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.handlers.ResourceHandler
import com.hamburguesa66.scopa.ui.shared.BasePane
import com.hamburguesa66.scopa.ui.shared.ImageWithSubtitlePane
import javax.swing.*

class TitlePane : BasePane() {

    private val credits = "<html><p>A game by <a href=\"\"> @hamburguesa66</a></p><p style=\"text-align:center;\">&copy;2023-2024</p></html>"

    init {
        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        container.add(
            ImageWithSubtitlePane(
                image = ResourceHandler.getSprite(ResourceHandler.Sprite.GAME_LOGO)
            )
        )

        container.add(
            ImageWithSubtitlePane(
                image = ResourceHandler.getSprite(ResourceHandler.Sprite.COMPANY_LOGO),
                subtitle = credits
            )
        )

        add(container)
    }
}