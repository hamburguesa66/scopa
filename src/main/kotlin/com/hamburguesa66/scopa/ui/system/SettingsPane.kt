package com.hamburguesa66.scopa.ui.system

import com.hamburguesa66.scopa.handlers.SettingsHandler
import com.hamburguesa66.scopa.ui.MainFrame
import java.awt.FlowLayout
import javax.swing.JComboBox
import javax.swing.JDialog
import javax.swing.JLabel
import javax.swing.JPanel

class SettingsPane(
    private val mainFrame: MainFrame,
    title: String = "Settings",
    private val settingsHandler: SettingsHandler
) : JDialog(mainFrame, title, true) {

    init {
        contentPane.layout = FlowLayout(FlowLayout.LEFT)

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

        val panelA = JPanel(FlowLayout())
        val comboBoxLabel = JLabel("CPU Opponent")

        val comboBox = JComboBox(settingsHandler.cpuAlgorithmOptionMapper.labels().toTypedArray())
        comboBox.selectedItem = settingsHandler.cpuAlgorithmOptionMapper.optionToLabel(settingsHandler.settings.cpuAlgorithm)
        comboBox.addItemListener {
            if(it.stateChange == 1) {
                println("The ${it.item} was selected")
                settingsHandler.updateCpuAlgorithm(
                    settingsHandler.cpuAlgorithmOptionMapper.labelToOption(it.item as String)
                )
            }
        }

        panelA.add(comboBoxLabel)
        panelA.add(comboBox)

        contentPane.add(panelA)

        render()
    }

    private fun render() {
        setSize(640, 480)
        setLocationRelativeTo(mainFrame)
        isVisible = true
    }

}