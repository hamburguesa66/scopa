package com.hamburguesa66.scopa.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PlayerModelTest {

    @Test
    fun executePlay_WithOnlyHandCard_ShouldReturnTrue() {
        // Arrange.
        val tableCards = mutableListOf<Card>()
        val handCard = Card(suite = CardSuite.GOLDS, score =7)
        val playerModel = PlayerModel(
            hand = mutableListOf(handCard),
            handCard = handCard,
            tableCards = mutableListOf()
        )
        // Act.
        playerModel.executePlay(gameCards = tableCards)
        // Assert.
        Assertions.assertFalse { playerModel.hand.contains(handCard) }
        Assertions.assertTrue { playerModel.handCard == null }
        Assertions.assertTrue { playerModel.tableCards.isEmpty() }
        Assertions.assertTrue { playerModel.cleanings == 0 }
        Assertions.assertTrue { playerModel.deck.isEmpty() }
    }

    @Test
    fun executePlay_ScoreIs15_ShouldReturnTrue() {
        // Arrange.
        val selectedTableCard = Card(suite = CardSuite.CUPS, score = 8)
        val tableCards = mutableListOf(
            Card(suite = CardSuite.CUPS, score = 1),
            selectedTableCard
        )
        val handCard = Card(suite = CardSuite.GOLDS, score = 7)
        val playerModel = PlayerModel(
            hand = mutableListOf(handCard),
            handCard = handCard,
            tableCards = mutableListOf(selectedTableCard)
        )
        // Act.
        playerModel.executePlay(gameCards = tableCards)
        // Assert.
        Assertions.assertFalse { playerModel.hand.contains(handCard) }
        Assertions.assertTrue { playerModel.handCard == null }
        Assertions.assertTrue { playerModel.tableCards.isEmpty() }
        Assertions.assertTrue { playerModel.cleanings == 0 }
        Assertions.assertTrue { playerModel.deck.contains(handCard) }
        Assertions.assertTrue { playerModel.deck.contains(selectedTableCard) }
        Assertions.assertTrue { !tableCards.contains(selectedTableCard) }
        Assertions.assertTrue { tableCards.isNotEmpty() }
    }

    @Test
    fun executePlay_Cleaning_ShouldReturnTrue() {
        // Arrange.
        val selectedTableCard = Card(suite = CardSuite.CUPS, score = 8)
        val tableCards = mutableListOf(selectedTableCard)
        val handCard = Card(suite = CardSuite.GOLDS, score = 7)
        val playerModel = PlayerModel(
            hand = mutableListOf(handCard),
            handCard = handCard,
            tableCards = mutableListOf(selectedTableCard)
        )
        // Act.
        playerModel.executePlay(gameCards = tableCards)
        // Assert.
        Assertions.assertFalse { playerModel.hand.contains(handCard) }
        Assertions.assertTrue { playerModel.handCard == null }
        Assertions.assertTrue { playerModel.tableCards.isEmpty() }
        Assertions.assertTrue { playerModel.cleanings == 1 }
        Assertions.assertTrue { playerModel.deck.contains(handCard) }
        Assertions.assertTrue { playerModel.deck.contains(selectedTableCard) }
        Assertions.assertTrue { !tableCards.contains(selectedTableCard) }
        Assertions.assertTrue { tableCards.isEmpty() }
    }

    @Test
    fun executePlay_ScoreIsNot15_ShouldReturnFalse() {
        // Arrange.
        val selectedTableCard = Card(suite = CardSuite.CUPS, score =7)
        val handCard = Card(suite = CardSuite.GOLDS, score = 7)
        val tableCards = mutableListOf(selectedTableCard)
        val playerModel = PlayerModel(
            hand = mutableListOf(handCard),
            handCard = handCard,
            tableCards = mutableListOf(selectedTableCard)
        )
        // Act.
        playerModel.executePlay(gameCards = tableCards)
        // Assert.
        Assertions.assertFalse { playerModel.hand.contains(handCard) }
        Assertions.assertTrue { playerModel.handCard == null }
        Assertions.assertTrue { playerModel.tableCards.isEmpty() }
        Assertions.assertTrue { playerModel.deck.isEmpty() }
        Assertions.assertTrue { tableCards.contains(selectedTableCard) }
        Assertions.assertTrue { tableCards.contains(handCard) }
    }
}