package com.example.cards.domain

data class PlayerScore(
    val cleanings: Int,
    val cards: Int,
    val golds: Int,
    val sevens: Int,
    val joker: Int,
    var bonusPoints: Int
) {
    fun setBonusPoints(anotherPlayerScore: PlayerScore) {
        bonusPoints = 0
        if(cards > anotherPlayerScore.cards) {
            bonusPoints++
        }
        if(golds > anotherPlayerScore.golds) {
            bonusPoints++
        }
        if(sevens > anotherPlayerScore.sevens) {
            bonusPoints++
        }
        if(joker > anotherPlayerScore.joker) {
            bonusPoints++
        }
    }
    fun getFinalScore() : Int = cleanings + bonusPoints
}