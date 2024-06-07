package com.hamburguesa66.scopa.domain

import com.hamburguesa66.scopa.domain.strategies.CpuStrategy
import com.hamburguesa66.scopa.handlers.UIHandler

class Game(
    val aCpuStrategy: CpuStrategy,
    val uiHandler: UIHandler
) {
    val deck : MutableList<Card> = getNewDeck().shuffled().toMutableList()
    lateinit var state: GameState
    val table: MutableList<Card> = mutableListOf()
    val player: PlayerModel = PlayerModel()
    val cpu: PlayerModel = PlayerModel()

    fun start() {
        dealCardsToPlayers()
        // Place cards in the table
        repeat(4) {
            table.add(deck.removeAt(0))
        }
        state = GameState.PLAYER_TURN
        uiHandler.draw()
    }

    fun playerMove() {
        val result = player.executePlay(gameCards = table)
        if(!result) { uiHandler.showBadMoveDialog() }

        // Calculate CPU move
        val play = aCpuStrategy.makeMove(cpu.hand,table)
        cpu.handCard = cpu.hand[play.first]
        cpu.tableCards.addAll(play.second.map { table[it] })
        state = GameState.CPU_MOVE

        uiHandler.draw()
    }

    fun cpuMove() {
        cpu.executePlay(gameCards = table)
        state = GameState.PLAYER_TURN
        uiHandler.draw()
    }

    fun dealCardsToPlayers() {
        if(deck.isNotEmpty()) {
            repeat(3) {
                player.hand.add(deck.removeAt(0))
                cpu.hand.add(deck.removeAt(0))
            }
        } else {
            uiHandler.showGameOverDialog()
            state = GameState.ENDED
            uiHandler.draw()
        }
    }

    fun selectTableCard(aCard: Card) {
        player.addOrRemoveTableCard(aCard)
        uiHandler.draw()
    }

    fun selectHandCard(aCard: Card) {
        player.setOrClearHandCard(aCard)
        uiHandler.draw()
    }

    private fun getNewDeck() = CardSuite.entries
        .map { suite -> (1..10).map { Card(suite,it) } }
        .flatten()
}