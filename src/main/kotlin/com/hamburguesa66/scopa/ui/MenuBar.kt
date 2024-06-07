package com.hamburguesa66.scopa.ui

import com.hamburguesa66.scopa.handlers.UIHandler
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class MenuBar(private val handler: UIHandler) : JMenuBar() {

    init {
        val fileMenu = JMenu("File")

        val newGameItem = JMenuItem(object : AbstractAction("New Game") {
            override fun actionPerformed(e: ActionEvent) {
                handler.startNewGame()
            }
        })
        val settingsItem = JMenuItem(object : AbstractAction("Settings") {
            override fun actionPerformed(e: ActionEvent) {
                handler.openSettings()
            }
        })
        val exitItem = JMenuItem(object : AbstractAction("Exit") {
            override fun actionPerformed(e: ActionEvent) {
                handler.exit()
            }
        })

        fileMenu.add(newGameItem)
        fileMenu.add(settingsItem)
        fileMenu.add(exitItem)

        add(fileMenu)
    }

}