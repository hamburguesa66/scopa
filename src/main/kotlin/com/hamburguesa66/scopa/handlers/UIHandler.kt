package com.hamburguesa66.scopa.handlers

import com.hamburguesa66.scopa.domain.*
import com.hamburguesa66.scopa.domain.system.Settings
import com.hamburguesa66.scopa.ui.*
import com.hamburguesa66.scopa.ui.system.SettingsPane
import org.springframework.stereotype.Component
import java.awt.BorderLayout
import javax.swing.JOptionPane
import kotlin.system.exitProcess

@Component
class UIHandler(
    private val settingsHandler: SettingsHandler
) {

    private val mainFrame : MainFrame = MainFrame()
    private lateinit var game : Game
    private lateinit var settings : Settings

    init {
        mainFrame.addPane(TitlePane(), BorderLayout.CENTER)
        mainFrame.jMenuBar = MenuBar(this)
        mainFrame.validate()
        mainFrame.repaint()
        mainFrame.isVisible = true
    }

    fun startNewGame() {
        settings = settingsHandler.settings.copy()
        game = Game(
            aCpuStrategy = settingsHandler.cpuAlgorithmOptionMapper.optionToStrategy(
                settings.cpuAlgorithm
            ),
            uiHandler = this
        )
        game.start()
    }

    fun draw() {
        mainFrame.contentPane.removeAll()
        mainFrame.addPane(
            CpuPane(
                cards = game.cpu.hand,
                selectedCard = game.cpu.handCard,
                showCard = game.state == GameState.CPU_MOVE,
                showScore = settings.showScore,
                score = game.cpu.getScore(),
                avatar = settingsHandler.cpuAlgorithmOptionMapper.optionToSprite(settings.cpuAlgorithm)
            ),
            BorderLayout.NORTH
        )
        mainFrame.addPane(
            buildTablePane(),
            BorderLayout.CENTER
        )
        mainFrame.addPane(
            PlayerPane(
                cards = game.player.hand,
                selectedCard = game.player.handCard,
                enableCardSelection = game.state == GameState.PLAYER_TURN,
                onCardSelection = game::selectHandCard,
                showScore = settings.showScore,
                score = game.player.getScore()
            ),
            BorderLayout.SOUTH
        )
        mainFrame.validate()
        mainFrame.repaint()
    }

    private fun buildTablePane(): TableHandPane {
        return when(game.state) {
            GameState.CPU_MOVE -> TableHandPane(
                deck = game.deck,
                cards = game.table,
                selectedCards = game.cpu.tableCards,
                submitButtonText = "Continue",
                enableSubmitButton = true,
                enableCardSelection = false,
                onCardSelection = (game::selectTableCard),
                onSubmit = this::confirmCpuPlay
            )
            GameState.PLAYER_TURN -> TableHandPane(
                deck = game.deck,
                cards = game.table,
                selectedCards = game.player.tableCards,
                submitButtonText = "Submit Play",
                enableSubmitButton = game.player.handCard != null,
                enableCardSelection = true,
                onCardSelection = game::selectTableCard,
                onSubmit = this::submitPlayerMove
            )
            GameState.ENDED -> TableHandPane(
                deck = game.deck,
                cards = game.table,
                selectedCards = emptyList(),
                submitButtonText = "End Game",
                enableSubmitButton = true,
                enableCardSelection = false,
                onCardSelection = game::selectTableCard,
                onSubmit = this::showFinalScoreboard
            )
        }
    }

    fun submitPlayerMove() {
        game.playerMove()
    }

    fun confirmCpuPlay() {
        game.cpuMove()
        if(game.cpu.hand.isEmpty()) {
            game.dealCardsToPlayers()
        }
        draw()
    }

    fun showFinalScoreboard() {
        mainFrame.contentPane.removeAll()

        val playerScore = game.player.getScore()
        val cpuScore = game.cpu.getScore()
        playerScore.setBonusPoints(cpuScore)
        cpuScore.setBonusPoints(playerScore)

        mainFrame.addPane(
            EndGamePane(
                playerScore = playerScore,
                cpuScore = cpuScore,
                cpuAvatar = settingsHandler.cpuAlgorithmOptionMapper.optionToSprite(settings.cpuAlgorithm)
            ),
            BorderLayout.CENTER
        )

        mainFrame.validate()
        mainFrame.repaint()
    }

    fun showBadMoveDialog() {
        JOptionPane.showMessageDialog(mainFrame, "Invalid move.")
    }

    fun showGameOverDialog() {
        JOptionPane.showMessageDialog(mainFrame, "There are no cards left in the deck. Game Over.")
    }

    fun openSettings() = SettingsPane(
        mainFrame = mainFrame,
        settingsHandler = settingsHandler
    )

    fun exit() {
        exitProcess(0)
    }

}