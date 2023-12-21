package com.example.cards.domain.strategies

import com.example.cards.domain.Card

class SimpleCpuStrategy : CpuStrategy {

    override fun makeMove(hand: List<Card>, table: List<Card>): Pair<Int, List<Int>> {
        val possibleMoves = hand.mapNotNull { card ->
            table
                .powerset()
                .firstOrNull { card.score + it.sumOf { it.score } == 15 }
                ?.let { set ->
                    Pair(
                        hand.indexOf(card),
                        set.map { table.indexOf(it) }.toList()
                    )
                }
        }
        return if (possibleMoves.isEmpty()) {
            Pair(0, emptyList())
        } else {
            possibleMoves[0]
        }
    }


    private fun <T> Collection<T>.powerset(): Set<Set<T>> =
        if (isEmpty()) setOf(emptySet())
        else drop(1)
            .powerset()
            .let { it+ it.map { it + first() } }

}