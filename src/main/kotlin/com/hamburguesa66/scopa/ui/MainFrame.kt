package com.hamburguesa66.scopa.ui

import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JFrame

class MainFrame : JFrame() {

    init {
        createUI()
        setTitle("Scopa")
        setSize(800, 600)
        setLocationRelativeTo(null)
        setDefaultCloseOperation(EXIT_ON_CLOSE)
    }

    private fun createUI() {
        contentPane.layout = BorderLayout()
    }

    fun addPane(component: JComponent, position: String) {
        contentPane.add(component, position)
    }

}