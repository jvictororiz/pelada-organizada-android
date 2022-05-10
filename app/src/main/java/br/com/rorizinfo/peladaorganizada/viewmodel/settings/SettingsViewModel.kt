package br.com.rorizinfo.peladaorganizada.viewmodel.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.data.persistence.BasePreference
import br.com.rorizinfo.peladaorganizada.viewmodel.common.SingleLiveEvent
import br.com.rorizinfo.peladaorganizada.data.persistence.LocalPreference
import br.com.rorizinfo.peladaorganizada.domain.usecase.CreateGameUseCase
import br.com.rorizinfo.peladaorganizada.repository.HistoryGameRepository
import br.com.rorizinfo.peladaorganizada.repository.PlayersPayRepository
import br.com.rorizinfo.peladaorganizada.viewmodel.settings.model.SettingsEvent
import br.com.rorizinfo.peladaorganizada.viewmodel.settings.model.SettingsModel

class SettingsViewModel(
    private val context: Context,
    private val localPreference: LocalPreference,
    private val useCase: CreateGameUseCase,
    private val payRepository: PlayersPayRepository,
    private val historyRepository: HistoryGameRepository,
) : ViewModel() {
    
    val screenLiveData = SettingsModel()
    val eventLiveData = SingleLiveEvent<SettingsEvent>()
    
    init {
        retrySettings()
    }
    
    private fun retrySettings() {
        screenLiveData.checkedTie.set(localPreference.getBoolean(BasePreference.SettingsHasTie(screenLiveData.currentGame.get() ?: 0)))
        screenLiveData.textPlayers.set(localPreference.get(BasePreference.SettingsNames(screenLiveData.currentGame.get() ?: 0)))
        screenLiveData.totalPlayers.set(localPreference.getInt(BasePreference.SettingsTotalPlayers(screenLiveData.currentGame.get() ?: 0)))
    }
    
    fun tapOnInit() {
        historyRepository.clearAll()
        payRepository.clearAll()
        if (validatePlayer(screenLiveData.players.get() ?: listOf())) {
            screenLiveData.run {
                localPreference.saveList(BasePreference.SettingsNames(screenLiveData.currentGame.get() ?: 0), screenLiveData.players.get())
                localPreference.save(BasePreference.SettingsHasTie(screenLiveData.currentGame.get() ?: 0), checkedTie.get() ?: true)
                localPreference.saveInt(BasePreference.SettingsTotalPlayers(screenLiveData.currentGame.get() ?: 0), totalPlayers.get() ?: 0)
            }
            SettingsEvent.GoToGameScreen(screenLiveData.currentGame.get() ?: 0).run()
        } else {
            SettingsEvent.AlertMessage(
                context.getString(R.string.message_error)
            ).run()
        }
    }
    
    fun choseGame() {
        retrySettings()
    }
    
    private fun validatePlayer(players: List<String>): Boolean {
        return useCase.invoke(players, screenLiveData.totalPlayers.get())
    }
    
    private fun SettingsEvent.run() {
        eventLiveData.value = this
    }
}