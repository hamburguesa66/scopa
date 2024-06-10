package com.hamburguesa66.scopa.domain.strategies

import com.hamburguesa66.scopa.domain.Card

class RandomCpuStrategy : CpuStrategy {

    override fun makeMove(hand: List<Card>, table: List<Card>): Pair<Int, List<Int>> {
        // TODO: Implement proper logic.
        return Pair(0, emptyList())
    }

}