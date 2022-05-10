package br.com.rorizinfo.peladaorganizada.ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rorizinfo.peladaorganizada.domain.model.Player
import br.com.rorizinfo.peladaorganizada.databinding.ItemPlayerBinding

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    
    var listPlayers = listOf<Player>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    
    var onClickPlayer: ((Player) -> Unit)? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPlayers[position])
    }
    
    override fun getItemCount() = listPlayers.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPlayerBinding.bind(view)
        
        fun bind(player: Player) {
            binding.tvName.text = player.name
            binding.body.setOnClickListener {
                onClickPlayer?.invoke(player)
            }
        }
    }
}