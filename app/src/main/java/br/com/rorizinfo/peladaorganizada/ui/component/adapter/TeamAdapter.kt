package br.com.rorizinfo.peladaorganizada.ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.domain.model.Team
import br.com.rorizinfo.peladaorganizada.databinding.ItemTeamBinding
import br.com.rorizinfo.peladaorganizada.domain.model.Player

class TeamAdapter(
    private val initTitle: Int
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    
    var onClickPlayer: ((Player) -> Unit)? = null
    
    var listTeams = listOf<Team>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTeams[position])
    }
    
    override fun getItemCount() = listTeams.size
    
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTeamBinding.bind(view)
        
        fun bind(team: Team) {
            binding.teamView.enableClickTeam = false
            binding.teamView.team = team
            binding.teamView.title = view.context.getString(R.string.title_team, adapterPosition + initTitle)
            binding.teamView.onClickPlayer = { player ->
                onClickPlayer?.invoke(player)
            }
        }
    }
}