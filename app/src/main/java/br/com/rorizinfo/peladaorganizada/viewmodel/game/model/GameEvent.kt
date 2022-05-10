package br.com.rorizinfo.peladaorganizada.viewmodel.game.model

import br.com.rorizinfo.peladaorganizada.domain.model.PlayerPay
import br.com.rorizinfo.peladaorganizada.domain.model.Team
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.model.MenuItemModel

sealed class GameEvent {
    data class ShowManagerPayment(val listPlayers: List<PlayerPay>) : GameEvent()
    data class LoadGame(val listGames: List<Team>) : GameEvent()
    data class AlertMessage(val message: String, val event: (() -> Unit)? = null) : GameEvent()
    data class ShowNewPlayer(val onClick: () -> Unit) : GameEvent()
    data class ShowMenu(val namePlayer: String, val itensMenu: List<MenuItemModel>) : GameEvent()
}