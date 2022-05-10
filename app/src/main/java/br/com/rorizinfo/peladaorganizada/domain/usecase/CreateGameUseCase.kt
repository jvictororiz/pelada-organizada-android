package br.com.rorizinfo.peladaorganizada.domain.usecase

class CreateGameUseCase {
    
    operator fun invoke(players: List<String>, playersByTeam: Int?): Boolean {
        playersByTeam?.let { playersTeam ->
            return players.size >= playersByTeam * 2 && !containsEqualsNames(players)
        }
        return false
    }
    
    private fun containsEqualsNames(players: List<String>): Boolean {
        val playersMutable = players.toMutableList()
        return players.any {
            playersMutable.remove(it)
            playersMutable.contains(it)
        }
    }
}