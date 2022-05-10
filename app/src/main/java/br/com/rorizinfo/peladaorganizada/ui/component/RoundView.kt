package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.rorizinfo.peladaorganizada.databinding.RoundViewBinding
import br.com.rorizinfo.peladaorganizada.domain.model.Player
import br.com.rorizinfo.peladaorganizada.domain.model.Team


class RoundView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    var onClickTeam: ((Team, Team, Int) -> Unit)? = null
    var onClickPlayer: ((Int, Player) -> Unit)? = null
    
    val binding by lazy {
        RoundViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    var enableStatus: Boolean = false
    set(value) {
        field = value
        binding.teamOne.enableStatus =value
        binding.teamTwo.enableStatus =value
    }
    
    var teamOne: Team = Team()
        set(value) {
            field = value
            binding.teamOne.team = value
            
        }
    
    var teamTwo: Team = Team()
        set(value) {
            field = value
            binding.teamTwo.team = value
        }
    
    
    init {
        binding.teamOne.onClickTeam = {
            onClickTeam?.invoke(binding.teamOne.team, binding.teamTwo.team, 1)
        }
    
        binding.teamTwo.onClickTeam = {
            onClickTeam?.invoke(binding.teamOne.team, binding.teamTwo.team, 2)
        }
        
        binding.teamOne.onClickPlayer = {
            onClickPlayer?.invoke(1, it )
        }
        binding.teamTwo.onClickPlayer = {
            onClickPlayer?.invoke(2, it )
        }
    }
    
}