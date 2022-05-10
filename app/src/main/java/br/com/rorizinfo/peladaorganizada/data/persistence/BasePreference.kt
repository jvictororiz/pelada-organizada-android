package br.com.rorizinfo.peladaorganizada.data.persistence


sealed class BasePreference(val key: String) {
    data class SettingsNames(val fut: Int) : BasePreference(fut.toString() + "_names")
    data class SettingsTotalPlayers(val fut: Int) : BasePreference(fut.toString() + "_TotalPlayers")
    data class SettingsHasTie(val fut: Int) : BasePreference(fut.toString() + "_HasTie")
}