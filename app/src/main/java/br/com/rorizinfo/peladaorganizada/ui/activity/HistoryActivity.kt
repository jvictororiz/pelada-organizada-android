package br.com.rorizinfo.peladaorganizada.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.ActivityHistoryBinding
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.RoundAdapter
import br.com.rorizinfo.peladaorganizada.viewmodel.history.HistoryViewModel
import br.com.rorizinfo.peladaorganizada.viewmodel.history.model.HistoryGamesEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val adapter by lazy { RoundAdapter() }
    private val viewModel: HistoryViewModel by viewModel()
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history) as ActivityHistoryBinding
        binding.rvRounds.adapter = adapter
        setupListeners()
        setupObserver()
    }
    
    private fun setupObserver() {
        viewModel.eventLiveData.observe(this) { event ->
            when (event) {
                is HistoryGamesEvent.LoadGames -> {
                    binding.rvRounds.isVisible = true
                    binding.tvEmpty.isVisible = false
                    adapter.listRounds = event.listGames
                }
                HistoryGamesEvent.EmptyList -> {
                    binding.rvRounds.isVisible = false
                    binding.tvEmpty.isVisible = true
                }
            }
        }
    }
    
    private fun setupListeners() {
        binding.toolbar.setOnBackClickListener {
            finish()
        }
    }
}