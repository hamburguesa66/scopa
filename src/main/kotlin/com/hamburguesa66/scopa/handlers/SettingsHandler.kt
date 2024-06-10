package com.hamburguesa66.scopa.handlers

import com.hamburguesa66.scopa.domain.system.CpuAlgorithmOption
import com.hamburguesa66.scopa.domain.system.CpuAlgorithmOptionMapper
import com.hamburguesa66.scopa.domain.system.Settings
import org.springframework.stereotype.Component
import java.util.prefs.Preferences

@Component
class SettingsHandler {

    private var preferences: Preferences = Preferences.userNodeForPackage(SettingsHandler::class.java)
    lateinit var settings: Settings
    val cpuAlgorithmOptionMapper = CpuAlgorithmOptionMapper()

    init {
        updateSettingsWithCurrentPreferences()

        preferences.addPreferenceChangeListener {
            println("Preference change detected: ${it.key} -> ${it.newValue}")
            updateSettingsWithCurrentPreferences()
            println("Settings were updated")
        }
    }

    fun updateShowScore(value: Boolean) {
        preferences.putBoolean("showScore", value)
    }

    fun updateCpuAlgorithm(value: CpuAlgorithmOption) {
        preferences.put("cpuAlgorithm", value.name)
    }

    private fun updateSettingsWithCurrentPreferences() {
        settings = Settings(
            showScore = preferences.getBoolean("showScore", false),
            cpuAlgorithm = CpuAlgorithmOption.valueOf(
                preferences.get("cpuAlgorithm", CpuAlgorithmOption.SIMPLE.name)
            )
        )
    }

}