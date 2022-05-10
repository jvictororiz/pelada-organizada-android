package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.domain.model.StatusGame
import br.com.rorizinfo.peladaorganizada.domain.model.Team
import br.com.rorizinfo.peladaorganizada.databinding.TeamViewBinding
import br.com.rorizinfo.peladaorganizada.domain.model.Player
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.PlayerAdapter

class TeamView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    val binding by lazy {
        TeamViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private val adapter by lazy { PlayerAdapter() }
    
    var enableStatus: Boolean = false
//        set(value) {
//            if(value){
//                binding.card.setCardBackgroundColor(ContextCompat.getColor())
//            }
//        }
    
    var initializeComponent: (() -> Unit)? = null
    
    var enableClickTeam: Boolean
        get() = binding.tvTitle.isEnabled
        set(value) {
            binding.tvTitle.isEnabled = value
        }
    
    var team: Team = Team()
        set(value) {
            field = value
            adapter.listPlayers = value.players
            setupLayout(value.status)
        }
    
    var title: String = ""
        get() = binding.tvTitle.text.toString()
        set(value) {
            field = value
            binding.tvTitle.text = value
        }
    
    var onClickTeam: ((Team) -> Unit)? = null
    var onClickPlayer: ((Player) -> Unit)? = null
    set(value) {
        field = value
        adapter.onClickPlayer = onClickPlayer
    }
    
    init {
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.TeamView,
            defStyle,
            defStyleRes
        ).apply {
            binding.rvPlayers.adapter = adapter
            with(this@TeamView) {
                title = getString(R.styleable.TeamView_title) ?: ""
            }
        }
        binding.tvTitle.setOnClickListener {
            onClickTeam?.invoke(team)
        }
        initializeComponent?.invoke()
    }
    
    private fun setupLayout(status: StatusGame) {
        when (status) {
            StatusGame.Lose -> {
                binding.card.alpha = 0.7F
                binding.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lose))
            }
            StatusGame.Neutral -> {
                binding.card.alpha = 1.0F
                binding.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.gray))
            }
            StatusGame.Win -> {
                binding.card.alpha = 1.0F
                binding.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primaryColorDark))
            }
        }
    }
    
}