package com.example.cards.domain.strategies

import com.example.cards.domain.Card

interface CpuStrategy {

    fun makeMove(hand: List<Card>, table: List<Card>) : Pair<Int,List<Int>>

}