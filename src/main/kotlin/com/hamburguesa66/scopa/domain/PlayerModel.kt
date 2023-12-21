package com.hamburguesa66.scopa.domain

data class PlayerModel(
    val hand: MutableList<Card> = mutableListOf(),
    var handCard: Card? = null,
    val tableCards: MutableList<Card> = mutableListOf(),
    val deck: MutableList<Card> = mutableListOf(),
    var cleanings: Int = 0,
) {
    fun getScore() : PlayerScore = PlayerScore(
        cleanings = cleanings,
        cards = deck.size,
        golds = deck.count { it.suite == CardSuite.GOLDS },
        sevens = deck.count { it.score == 7 },
        joker = deck.count { it.score == 7 && it.suite == CardSuite.GOLDS },
        bonusPoints = 0
    )
}