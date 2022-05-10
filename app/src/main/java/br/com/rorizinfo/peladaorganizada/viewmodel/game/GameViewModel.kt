package br.com.rorizinfo.peladaorganizada.viewmodel.game

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.domain.model.*
import br.com.rorizinfo.peladaorganizada.data.persistence.BasePreference
import br.com.rorizinfo.peladaorganizada.data.persistence.LocalPreference
import br.com.rorizinfo.peladaorganizada.domain.usecase.AddNewPlayersUseCase
import br.com.rorizinfo.peladaorganizada.repository.HistoryGameRepository
import br.com.rorizinfo.peladaorganizada.repository.PlayersPayRepository
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.model.MenuItemModel
import br.com.rorizinfo.peladaorganizada.ui.ext.copy
import br.com.rorizinfo.peladaorganizada.viewmodel.common.SingleLiveEvent
import br.com.rorizinfo.peladaorganizada.viewmodel.game.model.GameEvent
import br.com.rorizinfo.peladaorganizada.viewmodel.game.model.GameModel

class GameViewModel(
    private val context: Context,
    private val useCase: AddNewPlayersUseCase,
    private val localPreference: LocalPreference,
    private val historyRepository: HistoryGameRepository,
    private val payRepository: PlayersPayRepository,
    private val settingsSelected: Int
) : ViewModel() {
    
    private var totalPlayersByTeam: Int = -1
    val screenLiveData = GameModel()
    val eventLiveData = SingleLiveEvent<GameEvent>()
    
    init {
        screenLiveData.checkedTie.set(localPreference.getBoolean(BasePreference.SettingsHasTie(settingsSelected)))
        val players = localPreference.getList(BasePreference.SettingsNames(settingsSelected))
        totalPlayersByTeam = localPreference.getInt(BasePreference.SettingsTotalPlayers(settingsSelected))
        payRepository.addAllPlayers(players.map { PlayerPay(Player(it)) })
        initializeGame(players.shuffled())
    }
    
    private fun initializeGame(players: List<String>) {
        val randomPlayers = players.map { Player(it) }.toMutableList()
        val nowGame = generateRound(randomPlayers)
        val generateOhtersTime: List<Team> = generateOthersTeam(nowGame.othersTeam)
        screenLiveData.game.set(nowGame)
        GameEvent.LoadGame(generateOhtersTime).run()
    }
    
    private fun generateOthersTeam(othersPlayers: List<Player>): MutableList<Team> {
        val listTeams = mutableListOf(Team())
        var currentIndex = 0
        othersPlayers.forEach { player ->
            if (currentIndex == totalPlayersByTeam) {
                currentIndex = 0
                listTeams.add(Team())
            }
            listTeams[listTeams.size - 1].players.add(player)
            currentIndex++
        }
        return listTeams
    }
    
    private fun generateRound(players: MutableList<Player>): Game {
        val teamOne = Team(players.subList(0, totalPlayersByTeam), StatusGame.Neutral).copy()
        val teamTwo = Team(players.subList(totalPlayersByTeam, totalPlayersByTeam * 2), StatusGame.Neutral).copy()
        val othersPlayers = ArrayList(players)
        othersPlayers.removeAll(teamOne.players)
        othersPlayers.removeAll(teamTwo.players)
        return Game(
            currentRound = Round(teamOne, teamTwo),
            othersTeam = othersPlayers
        )
    }
    
    fun tapOnRoundViewTeam(teamOne: Team, teamTwo: Team, teamWinner :Int) {
        GameEvent.AlertMessage(
            context.getString(R.string.message_confirm_win_team)
        ) {
            if(teamWinner == 1){
                historyRepository.addHistory(RoudModel(teamOne.apply { status = StatusGame.Win }, teamTwo.apply { status = StatusGame.Lose }))
            }else{
                historyRepository.addHistory(RoudModel(teamOne.apply { status = StatusGame.Lose }, teamTwo.apply { status = StatusGame.Win }))
            }
            val nextPlayers = (screenLiveData.game.get()?.othersTeam ?: mutableListOf())
            nextPlayers.addAll(0, teamOne.players)
            nextPlayers.addAll(teamTwo.players)
            val nowGame = generateRound(nextPlayers)
            val generateOhtersTime: List<Team> = generateOthersTeam(nowGame.othersTeam)
            screenLiveData.game.set(nowGame)
            GameEvent.LoadGame(generateOhtersTime).run()
        }.run()
    }
    
    fun tapOnTie(firtTeam: Int) {
        val nextPlayers = screenLiveData.game.get()?.othersTeam ?: mutableListOf()
        val teamOne = screenLiveData.game.get()?.currentRound?.teamOne?.players ?: mutableListOf()
        val teamTwo = screenLiveData.game.get()?.currentRound?.teamTwo?.players ?: mutableListOf()
        if (firtTeam == 1) {
            nextPlayers.addAll(teamOne)
            nextPlayers.addAll(teamTwo)
        } else {
            nextPlayers.addAll(teamTwo)
            nextPlayers.addAll(teamOne)
        }
        historyRepository.addHistory(RoudModel(Team(teamOne, StatusGame.Neutral), Team(teamTwo, StatusGame.Neutral)))
        val nowGame = generateRound(nextPlayers)
        val generateOhtersTime: List<Team> = generateOthersTeam(nowGame.othersTeam)
        screenLiveData.game.set(nowGame)
        GameEvent.LoadGame(generateOhtersTime).run()
    }
    
    fun tapOnNewPlayers() {
        GameEvent.ShowNewPlayer {
            val newPlayers = screenLiveData.newPlayers.get()
            if (!newPlayers.isNullOrEmpty() && useCase(screenLiveData.game.get()?.othersTeam, newPlayers)) {
                payRepository.addAllPlayers(newPlayers.map { name -> PlayerPay(Player(name)) })
                screenLiveData.game.get()?.let { nowGame ->
                    nowGame.othersTeam.addAll(newPlayers.map { Player(it) })
                    val generateOhtersTime: List<Team> = generateOthersTeam(nowGame.othersTeam)
                    screenLiveData.game.set(nowGame)
                    localPreference.addList(BasePreference.SettingsNames(settingsSelected), newPlayers)
                    GameEvent.LoadGame(generateOhtersTime).run()
                }
            }
        }.run()
    }
    
    fun tapOnRoundViewPlayer(team: Int, player: Player) {
        GameEvent.ShowMenu(
            player.name,
            listOf(
                MenuItemModel(
                    title = context.getString(R.string.menu_player_exit),
                    description = context.getString(R.string.menu_player_exit_description),
                    colorTitle = R.color.lose,
                    eventClick = { removeFromListPlayer(player, team) }
                ))
        ).run()
    }
    
    private fun removeFromListPlayer(player: Player, team: Int) {
        payRepository.updateTimeExitPlayer(player)
        screenLiveData.game.get()?.copy()?.let { nowGame ->
            val teamOne = nowGame.currentRound.teamOne.players.copy()
            val teamTwo = nowGame.currentRound.teamTwo.players.copy()
            if (team == 1) {
                teamOne.remove(player)
                teamOne.add(0, nowGame.othersTeam.first())
            } else {
                teamTwo.remove(player)
                teamTwo.add(0, nowGame.othersTeam.first())
            }
            nowGame.othersTeam.removeFirst()
            screenLiveData.game.set(
                Game(
                    currentRound = Round(Team(teamOne), Team(teamTwo)),
                    othersTeam = nowGame.othersTeam
                )
            )
            GameEvent.LoadGame(generateOthersTeam(nowGame.othersTeam)).run()
            
        }
    }
    
    fun tapOnNextPlayer(player: Player) {
        GameEvent.ShowMenu(
            player.name,
            listOf(
                MenuItemModel(
                    title = context.getString(R.string.menu_player_exit),
                    description = context.getString(R.string.menu_player_exit_description),
                    colorTitle = R.color.lose,
                    eventClick = {
                        payRepository.updateTimeExitPlayer(player)
                        screenLiveData.game.get()?.copy()?.let { nowGame ->
                            nowGame.othersTeam.remove(player)
                            GameEvent.LoadGame(generateOthersTeam(nowGame.othersTeam)).run()
                        }
                    }
                ))
        ).run()
    }
    
    fun updatePaymentPlayers(players: List<PlayerPay>) {
        payRepository.replaceAllPlayers(players.toMutableList())
    }
    
    fun tapOnManagerPayment() {
        GameEvent.ShowManagerPayment(
            payRepository.getAllPlayers()
        ).run()
    }
    
    private fun GameEvent.run() {
        eventLiveData.value = this
    }
    
}