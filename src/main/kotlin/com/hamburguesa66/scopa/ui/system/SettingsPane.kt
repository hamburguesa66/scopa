package com.hamburguesa66.scopa.ui.system

import com.hamburguesa66.scopa.handlers.SettingsHandler
import com.hamburguesa66.scopa.ui.MainFrame
import java.awt.FlowLayout
import javax.swing.JDialog
import javax.swing.JLabel

class SettingsPane(
    private val mainFrame: MainFrame,
    title: String = "Settings",
    private val settingsHandler: SettingsHandler
) : JDialog(mainFrame, title, true) {

    init {
        contentPane.layout = FlowLayout()

        contentPane.add(
            JLabel("Note: new settings will be applied when starting a new game.")
        )

        contentPane.add(
            CheckboxInput(
                label = "Show current score (cleanings, 7 Münzen, sevens, golds and cards)",
                defaultValue = settingsHandler.settings.showScore,
                callback = settingsHandler::updateShowScore
            )
        )

        render()
    }

    private fun render() {
        setSize(640, 480)
        setLocationRelativeTo(mainFrame)
        isVisible = true
    }

}