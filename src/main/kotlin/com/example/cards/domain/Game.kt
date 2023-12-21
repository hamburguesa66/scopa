package com.example.cards.domain

import com.example.cards.domain.strategies.CpuStrategy
import com.example.cards.handlers.UIHandler

class Game(
    val aCpuStrategy: CpuStrategy,
    val uiHandler: UIHandler
) {
    private val deck : MutableList<Card> = getDeck().shuffled().toMutableList()
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
        if(player.tableCards.isEmpty()) {
            player.hand.remove(player.handCard)
            table.add(player.handCard!!)
        } else {
            val score = player.tableCards.sumOf { it.score } + player.handCard!!.score
            if(score == 15) {
                player.hand.remove(player.handCard!!)
                player.deck.add(player.handCard!!)
                player.tableCards.forEach {
                    player.deck.add(it)
                    table.remove(it)
                }
                if(table.isEmpty()) {
                    player.cleanings++
                }
            } else {
                uiHandler.showBadMoveDialog()
                player.hand.remove(player.handCard!!)
            }
        }
        player.handCard = null
        player.tableCards.clear()

        // Calculate CPU move
        val play = aCpuStrategy.makeMove(cpu.hand,table)
        cpu.handCard = cpu.hand[play.first]
        cpu.tableCards.addAll(play.second.map { table[it] })
        state = GameState.CPU_MOVE

        uiHandler.draw()
    }

    fun cpuMove() {
        if(cpu.tableCards.isEmpty()) {
            cpu.hand.remove(cpu.handCard)
            table.add(cpu.handCard!!)
        } else {
            cpu.hand.remove(cpu.handCard!!)
            cpu.deck.add(cpu.handCard!!)
            cpu.tableCards.forEach {
                cpu.deck.add(it)
                table.remove(it)
            }
            if(table.isEmpty()) { cpu.cleanings++ }
        }
        cpu.handCard = null
        cpu.tableCards.clear()
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
        if(player.tableCards.contains(aCard)) {
            player.tableCards.remove(aCard)
        } else {
            player.tableCards.add(aCard)
        }
        uiHandler.draw()
    }

    fun selectHandCard(aCard: Card) {
        player.handCard = if(player.handCard == aCard) {
            null
        } else {
            aCard
        }
        uiHandler.draw()
    }

    private fun getDeck() = CardSuite.entries
        .map { suite -> (1..10).map { Card(suite,it) } }
        .flatten()
}