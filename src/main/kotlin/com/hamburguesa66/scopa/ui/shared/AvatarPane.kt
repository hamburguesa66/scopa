package com.hamburguesa66.scopa.ui.shared

import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.border.EtchedBorder
import javax.swing.border.TitledBorder

class AvatarPane(
    private val image: URL,
    private val name: String,
    private val useAltNamePosition: Boolean = false
) : JLabel() {
    init {
        icon = ImageIcon(image)
        border = if(useAltNamePosition) {
            TitledBorder(EtchedBorder(), name, TitledBorder.LEFT, TitledBorder.BOTTOM)
        } else {
            TitledBorder(name)
        }
    }

}