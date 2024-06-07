package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.handlers.ResourceHandler
import com.hamburguesa66.scopa.handlers.UIHandler
import com.hamburguesa66.scopa.ui.system.MenuItem
import com.hamburguesa66.scopa.ui.system.MenuItemGroup
import javax.swing.JMenuBar

class MenuBar(private val handler: UIHandler) : JMenuBar() {

    init {
        add(
            MenuItemGroup(
                title = "File",
                emoji = ResourceHandler.Emoji.FOLDER,
                items = listOf(
                    MenuItem(
                        title = "New Game",
                        callback = handler::startNewGame,
                        emoji = ResourceHandler.Emoji.ROCKET
                    ),
                    MenuItem(
                        title = "Settings",
                        callback = handler::openSettings,
                        emoji = ResourceHandler.Emoji.NUT_AND_BOLT
                    ),
                    MenuItem(
                        title = "Exit",
                        callback = handler::exit,
                        emoji = ResourceHandler.Emoji.DOOR
                    )
                )
            )
        )
    }

}