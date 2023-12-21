package com.hamburguesa66.scopa.domain.strategies

import com.hamburguesa66.scopa.domain.Card

interface CpuStrategy {

    fun makeMove(hand: List<Card>, table: List<Card>) : Pair<Int,List<Int>>

}