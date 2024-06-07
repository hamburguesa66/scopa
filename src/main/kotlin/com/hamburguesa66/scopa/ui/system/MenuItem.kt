package com.hamburguesa66.scopa.ui.system

import com.hamburguesa66.scopa.handlers.ResourceHandler
import java.awt.Image.SCALE_SMOOTH
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.ImageIcon
import javax.swing.JMenu
import javax.swing.JMenuItem

class MenuItemGroup(
    title: String,
    items: List<MenuItem>,
    emoji: ResourceHandler.Emoji
) : JMenu(title) {

    init {
        items.forEach {
            add(it)
        }

        icon = getResizedEmoji(emoji)
    }
}

class MenuItem(
    private val title: String,
    private val callback: () -> Unit,
    emoji: ResourceHandler.Emoji,
) : JMenuItem(object : AbstractAction(title) {
    override fun actionPerformed(e: ActionEvent) {
        callback()
    }
}) {

    init {
        icon = getResizedEmoji(emoji)
    }

}

private fun getResizedEmoji(emoji: ResourceHandler.Emoji) = ImageIcon(
    ImageIcon(
        ResourceHandler.getEmoji(emoji)
    ).image.getScaledInstance(
        16, 16, SCALE_SMOOTH
    )
)