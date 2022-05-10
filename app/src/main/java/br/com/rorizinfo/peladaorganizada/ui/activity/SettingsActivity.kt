package br.com.rorizinfo.peladaorganizada.ui.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.ActivitySettingsBinding
import br.com.rorizinfo.peladaorganizada.ui.activity.GameActivity.Companion.EXTRA_SETTINGS_GAME
import br.com.rorizinfo.peladaorganizada.ui.ext.alertMessage
import br.com.rorizinfo.peladaorganizada.viewmodel.settings.model.SettingsEvent
import br.com.rorizinfo.peladaorganizada.viewmodel.settings.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings) as ActivitySettingsBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupListener()
        setupViewModel()
        
    }
    
    private fun setupViewModel() {
        viewModel.eventLiveData.observe(this) { event ->
            when (event) {
                is SettingsEvent.GoToGameScreen -> {
                    startActivity(Intent(this, GameActivity::class.java).apply {
                        putExtra(EXTRA_SETTINGS_GAME, event.settingsSelected)
                    })
                }
                is SettingsEvent.AlertMessage -> {
                    alertMessage(event.message)
                }
            }
        }
    }
    
    private fun setupListener() {
        binding.choseGame.onChangeTab = {
            viewModel.choseGame()
        }
        
        binding.btnSave.setOnClickListener {
            viewModel.tapOnInit()
        }
    }
}