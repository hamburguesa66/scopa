package com.hamburguesa66.scopa.ui.shared

import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.border.TitledBorder

class AvatarPane(
    private val image: URL,
    private val name: String
) : JLabel() {
    init {
        icon = ImageIcon(image)
        border = TitledBorder(name)
    }

}