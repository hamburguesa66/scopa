package com.example.cards.ui

import com.example.cards.domain.PlayerScore
import java.awt.Font
import java.awt.GridBagLayout
import java.awt.GridLayout
import java.net.URL
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder
import javax.swing.table.DefaultTableModel

class EndGamePane(
    val playerScore : PlayerScore,
    val cpuScore: PlayerScore
) : JPanel() {

    init {
        isOpaque = false
        layout = GridBagLayout()

        val container = JPanel()
        container.isOpaque = false
        container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

        val scoreboardPanel = JPanel()
        scoreboardPanel.layout = GridLayout(0,2)
        scoreboardPanel.add(createPlayerScorePanel(
            avatarPicture = ResourceLoader.getAvatarSprite(),
            playerName = "CPU",
            score = cpuScore
        ))
        scoreboardPanel.add(createPlayerScorePanel(
            avatarPicture = ResourceLoader.getAvatarSprite(),
            playerName = "Player",
            score = playerScore
        ))

        container.add(scoreboardPanel)
        container.add(createFinalScoreDetailsPanel())
        container.add(createWinnerIndicatorPanel())

        add(container)
    }

    private fun createPlayerScorePanel(
        avatarPicture: URL,
        playerName: String,
        score: PlayerScore
    ) : JPanel {
        val grid = JPanel()
        grid.layout = GridLayout(2,0)

        val container = JPanel()
        container.layout = GridBagLayout()

        val playerAvatar = JLabel(ImageIcon(avatarPicture))
        playerAvatar.border = TitledBorder(playerName)
        container.add(playerAvatar)

        val playerScore = JLabel(score.getFinalScore().toString(), SwingConstants.CENTER)
        playerScore.font = Font(null, Font.PLAIN, 36)

        grid.add(container)
        grid.add(playerScore)

        return grid
    }

    private fun createFinalScoreDetailsPanel() : JPanel {
        val columnNames = arrayOf("Category", "CPU", "Player")
        val data =
            arrayOf(
                arrayOf("Cleanings", cpuScore.cleanings, playerScore.cleanings),
                arrayOf("Cards", cpuScore.cards, playerScore.cards),
                arrayOf("Golds", cpuScore.golds, playerScore.golds),
                arrayOf("Sevens", cpuScore.sevens, playerScore.sevens),
                arrayOf("7 Münzen", cpuScore.joker, playerScore.joker),
                arrayOf("Total", cpuScore.getFinalScore(), playerScore.getFinalScore())
            )

        val model: DefaultTableModel = object : DefaultTableModel(data, columnNames) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            override fun getColumnClass(column: Int): Class<*> {
                return getValueAt(0, column).javaClass
            }
            override fun isCellEditable(row: Int, column: Int): Boolean {
                return false
            }
        }
        val table = JTable(model)
        table.preferredScrollableViewportSize = table.preferredSize

        val scrollPane = JScrollPane(table)
        scrollPane.border = TitledBorder("Final score in detail")

        val container = JPanel()
        container.layout = GridLayout(1,0)
        container.border = EmptyBorder(10,10,10,10)

        container.add(scrollPane)
        return container
    }

    private fun createWinnerIndicatorPanel() : JPanel {
        val container = JPanel()
        container.layout = GridLayout(1,0)

        val winnerName = if(playerScore.getFinalScore() > cpuScore.getFinalScore()) {
            "Player"
        } else {
            "CPU"
        }

        container.add(JLabel("$winnerName wins", SwingConstants.CENTER))
        return container
    }

}