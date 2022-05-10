package br.com.rorizinfo.peladaorganizada.viewmodel.settings.model

sealed class SettingsEvent {
    data class GoToGameScreen(val settingsSelected: Int) : SettingsEvent()
    data class AlertMessage(val message: String) : SettingsEvent()
}