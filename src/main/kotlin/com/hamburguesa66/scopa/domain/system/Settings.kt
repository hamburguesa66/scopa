package com.hamburguesa66.scopa.domain.system

enum class CpuAlgorithmOption {
    RANDOM, SIMPLE
}

data class Settings(
    val showScore: Boolean = false,
    val cpuAlgorithm: CpuAlgorithmOption = CpuAlgorithmOption.SIMPLE
)