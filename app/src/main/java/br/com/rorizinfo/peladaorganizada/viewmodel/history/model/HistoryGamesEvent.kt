package br.com.rorizinfo.peladaorganizada.viewmodel.history.model

import br.com.rorizinfo.peladaorganizada.domain.model.RoudModel
import br.com.rorizinfo.peladaorganizada.domain.model.Team
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.model.MenuItemModel

sealed class HistoryGamesEvent {
    data class LoadGames(val listGames: MutableList<RoudModel>) : HistoryGamesEvent()
    object EmptyList : HistoryGamesEvent()
}