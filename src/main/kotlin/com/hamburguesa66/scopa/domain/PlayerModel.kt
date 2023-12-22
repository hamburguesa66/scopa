package com.hamburguesa66.scopa.domain

data class PlayerModel(
    val hand: MutableList<Card> = mutableListOf(),
    var handCard: Card? = null,
    val tableCards: MutableList<Card> = mutableListOf(),
    val deck: MutableList<Card> = mutableListOf(),
    var cleanings: Int = 0,
) {
    fun executePlay(gameCards: MutableList<Card>) : Boolean {
        var result = true
        val score = tableCards.sumOf { it.score } + handCard!!.score

        if(score == 15) {
            deck.add(handCard!!)
            tableCards.forEach {
                deck.add(it)
                gameCards.remove(it)
            }
            if(gameCards.isEmpty()) { cleanings++ }
        } else {
            if(tableCards.isNotEmpty()) {
                result = false
            }
            gameCards.add(handCard!!)
        }

        resetHandAndTableCards()
        return result
    }

    fun setOrClearHandCard(aCard: Card) {
        handCard = if(handCard == aCard) {
            null
        } else {
            aCard
        }
    }

    fun addOrRemoveTableCard(aCard: Card) {
        if(tableCards.contains(aCard)) {
            tableCards.remove(aCard)
        } else {
            tableCards.add(aCard)
        }
    }

    fun getScore() : PlayerScore = PlayerScore(
        cleanings = cleanings,
        cards = deck.size,
        golds = deck.count { it.suite == CardSuite.GOLDS },
        sevens = deck.count { it.score == 7 },
        joker = deck.count { it.score == 7 && it.suite == CardSuite.GOLDS },
        bonusPoints = 0
    )

    private fun resetHandAndTableCards() {
        hand.remove(handCard!!)
        handCard = null
        tableCards.clear()
    }
}