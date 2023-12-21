package com.example.cards.ui

import com.example.cards.handlers.UIHandler
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
        val exitItem = JMenuItem(object : AbstractAction("Exit") {
            override fun actionPerformed(e: ActionEvent) {
                handler.exit()
            }
        })

        fileMenu.add(newGameItem)
        fileMenu.add(exitItem)

        add(fileMenu)
    }

}