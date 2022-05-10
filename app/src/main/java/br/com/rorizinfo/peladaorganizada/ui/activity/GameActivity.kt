package br.com.rorizinfo.peladaorganizada.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.ActivityGameBinding
import br.com.rorizinfo.peladaorganizada.ui.bottomSheet.AddNewPlayersBottomSheet
import br.com.rorizinfo.peladaorganizada.ui.bottomSheet.MenuBottomSheet
import br.com.rorizinfo.peladaorganizada.ui.bottomSheet.PlayerPayBottomSheet
import br.com.rorizinfo.peladaorganizada.ui.bottomSheet.model.AdNewPlayerModel
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.TeamAdapter
import br.com.rorizinfo.peladaorganizada.ui.ext.alertMessage
import br.com.rorizinfo.peladaorganizada.viewmodel.game.GameViewModel
import br.com.rorizinfo.peladaorganizada.viewmodel.game.model.GameEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModel {
        parametersOf(intent.extras?.getInt(EXTRA_SETTINGS_GAME))
    }
    
    private val newPlayerBottomSheet by lazy { AddNewPlayersBottomSheet() }
    private val menuBottomSheet by lazy { MenuBottomSheet() }
    private val paymentPlayersBottomSheet by lazy { PlayerPayBottomSheet() }
    
    private val adapter by lazy { TeamAdapter(3) }
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game) as ActivityGameBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupViews()
        setupListeners()
        setupObservers()
    }
    
    private fun setupViews() {
        binding.rvTeam.adapter = adapter
        newPlayerBottomSheet.model = AdNewPlayerModel(
            viewModel.screenLiveData.newPlayers
        )
    }
    
    private fun setupListeners() {
        binding.tieView.onConfirmTie = { team ->
            viewModel.tapOnTie(team)
        }
        binding.btnNewPlayers.setOnClickListener {
            viewModel.tapOnNewPlayers()
        }
        binding.roundView.onClickTeam = { teamClicked, otherTeam, winnerTeam ->
            viewModel.tapOnRoundViewTeam(teamClicked, otherTeam, winnerTeam)
        }
        
        binding.roundView.onClickPlayer = { team, player ->
            viewModel.tapOnRoundViewPlayer(team, player)
        }
        
        adapter.onClickPlayer = { player ->
            viewModel.tapOnNextPlayer(player)
        }
        
        binding.toolbar.setOnBackClickListener {
            alertMessage(getString(R.string.confirm_finish_game)) {
                finish()
            }
        }
        
        binding.toolbar.setOnRightClick {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        
        binding.btnPayment.setOnClickListener {
            viewModel.tapOnManagerPayment()
        }
        
        paymentPlayersBottomSheet.setOnCancelableListener{
            viewModel.updatePaymentPlayers(it)
        }
    }
    
    private fun setupObservers() {
        viewModel.eventLiveData.observe(this) { event ->
            when (event) {
                is GameEvent.LoadGame -> {
                    adapter.listTeams = event.listGames
                }
                is GameEvent.AlertMessage -> {
                    alertMessage(event.message, event.event)
                }
                is GameEvent.ShowNewPlayer -> {
                    newPlayerBottomSheet.model.confirmOnSuccess = event.onClick
                    newPlayerBottomSheet.show(supportFragmentManager)
                }
                is GameEvent.ShowMenu -> {
                    menuBottomSheet.listMenu = event.itensMenu
                    menuBottomSheet.namePlayer = event.namePlayer
                    menuBottomSheet.show(supportFragmentManager)
                }
                is GameEvent.ShowManagerPayment -> {
                    paymentPlayersBottomSheet.listPlayers = event.listPlayers
                    paymentPlayersBottomSheet.show(supportFragmentManager)
                }
            }
        }
    }
    
    override fun onBackPressed() {
        alertMessage(getString(R.string.confirm_finish_game)) {
            super.onBackPressed()
        }
    }
    
    companion object {
        const val EXTRA_SETTINGS_GAME = "EXTRA_SETTINGS_GAME"
    }
}