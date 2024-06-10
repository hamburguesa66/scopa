package com.hamburguesa66.scopa.domain.system

import com.hamburguesa66.scopa.domain.strategies.CpuStrategy
import com.hamburguesa66.scopa.domain.strategies.RandomCpuStrategy
import com.hamburguesa66.scopa.domain.strategies.SimpleCpuStrategy
import com.hamburguesa66.scopa.handlers.ResourceHandler

class CpuAlgorithmOptionMapper {

    private data class CpuAlgorithmOptionQuadruple(
        val option: CpuAlgorithmOption,
        val strategy: CpuStrategy,
        val label: String,
        val avatar: ResourceHandler.Sprite
    )

    private val values = listOf(
        CpuAlgorithmOptionQuadruple(
            option = CpuAlgorithmOption.SIMPLE,
            strategy = SimpleCpuStrategy(),
            label = "Monica",
            avatar = ResourceHandler.Sprite.MONICA_AVATAR
        ),
        CpuAlgorithmOptionQuadruple(
            option = CpuAlgorithmOption.RANDOM,
            strategy = RandomCpuStrategy(),
            label = "Tommy",
            avatar = ResourceHandler.Sprite.EMILY_AVATAR
        ),
    )

    fun labels() =
        values.map { it.label }

    fun labelToOption(label: String) =
        values.filter { it.label === label }.map { it.option }.first()

    fun optionToLabel(option: CpuAlgorithmOption) =
        values.filter { it.option === option }.map { it.label }.first()

    fun optionToStrategy(option: CpuAlgorithmOption) =
        values.filter { it.option === option }.map { it.strategy }.first()

    fun optionToSprite(option: CpuAlgorithmOption) =
        values.filter { it.option === option }.map { it.avatar }.first()

}