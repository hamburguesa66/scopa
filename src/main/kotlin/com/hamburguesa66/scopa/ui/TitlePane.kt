package com.hamburguesa66.scopa.ui

import java.awt.*
import java.net.URL
import javax.swing.*

class TitlePane : JPanel() {

    init {
        isOpaque = false
        layout = GridBagLayout()

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        container.add(createImageWithSubtitlePanel(
            ResourceLoader::class.java.getResource("/game-logo.png")!!
        ))

        container.add(createImageWithSubtitlePanel(
            ResourceLoader::class.java.getResource("/company-logo.png")!!,
            "<html> <p>A game by <a href=\"\"> @hamburguesa66</a></p><p style=\"text-align:center;\">&copy;2023-2024</p></html>"
        ))

        add(container)
    }

    private fun createImageWithSubtitlePanel(
        picture: URL,
        text: String? = null
    ) : JPanel {
        val grid = JPanel()
        grid.layout = GridLayout(2,0)

        val container = JPanel()
        container.layout = GridBagLayout()

        grid.add(container)

        val playerAvatar = JLabel(ImageIcon(picture))
        container.add(playerAvatar)

        text?.let {
            grid.add(JLabel(it, SwingConstants.CENTER))
        }

        return grid
    }

}