package br.com.rorizinfo.peladaorganizada.domain.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class Game(
    @Bindable val currentRound: Round,
    @Bindable  val othersTeam: MutableList<Player>
): BaseObservable() {
    fun getAllPlayers(): MutableList<Player> {
        return mutableListOf<Player>().apply {
            addAll(currentRound.teamOne.players)
            addAll(currentRound.teamTwo.players)
            addAll(othersTeam)
        }
    }
}





