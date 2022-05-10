package br.com.rorizinfo.peladaorganizada.viewmodel.settings.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import br.com.rorizinfo.peladaorganizada.ui.ext.SEPARATOR

data class SettingsModel(
    @Bindable var labelInputLiveData: ObservableField<String> = ObservableField(),
    @Bindable var totalPlayers: ObservableField<Int> = ObservableField(5),
    @Bindable var textPlayers: ObservableField<String> = ObservableField(""),
    @Bindable var checkedTie: ObservableField<Boolean> = ObservableField(true),
    @Bindable var players: ObservableField<MutableList<String>> = ObservableField(mutableListOf()),
    @Bindable var enableButtonNext: ObservableField<Boolean> = ObservableField(true),
    @Bindable var currentGame: ObservableField<Int> = ObservableField(0)
) : BaseObservable() {
    
    fun playersToString(): String {
        return players.get()?.joinToString(separator = SEPARATOR)?:""
    }
}
