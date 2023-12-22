package com.hamburguesa66.scopa.ui.shared

import java.awt.GridBagLayout
import java.awt.GridLayout
import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class ImageWithSubtitlePane(
    val image: URL,
    val subtitle: String? = null
) : JPanel() {

    init {
        layout = GridLayout(2,0)

        val imageContainer = JPanel(GridBagLayout())
        imageContainer.add(JLabel(ImageIcon(image)))
        add(imageContainer)

        subtitle?.let { add(JLabel(it, SwingConstants.CENTER)) }
    }

}