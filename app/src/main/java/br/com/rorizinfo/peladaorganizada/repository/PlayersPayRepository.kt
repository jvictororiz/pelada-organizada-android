package br.com.rorizinfo.peladaorganizada.repository

import br.com.rorizinfo.peladaorganizada.domain.model.Player
import br.com.rorizinfo.peladaorganizada.domain.model.PlayerPay
import java.util.*

class PlayersPayRepository {
    private var playersPay = mutableListOf<PlayerPay>()
    
    fun addAllPlayers(players: List<PlayerPay>) {
        playersPay.addAll(players)
    }
    
    fun updateTimeExitPlayer(player: Player) {
        playersPay[playersPay.indexOf(playersPay.find { it.player.name == player.name })].apply {
            timeExit = Date()
        }
    }
    
    fun replaceAllPlayers(players: MutableList<PlayerPay>) {
        playersPay = players
    }
    
    fun getAllPlayers() = playersPay
    fun clearAll() {
        playersPay.clear()
    }
    
}