package br.com.rorizinfo.peladaorganizada.domain.usecase

import br.com.rorizinfo.peladaorganizada.domain.model.Player

class AddNewPlayersUseCase {
    operator fun invoke(oldPlayers: MutableList<Player>?, newPlayers: List<String>): Boolean {
        val oldPlayersString = oldPlayers?.map { it.name }
        return newPlayers.find { oldPlayersString?.contains(it) == true } == null
    }
}