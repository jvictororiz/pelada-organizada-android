package br.com.rorizinfo.peladaorganizada.ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.ItemRoundBinding
import br.com.rorizinfo.peladaorganizada.databinding.ItemTeamBinding
import br.com.rorizinfo.peladaorganizada.domain.model.RoudModel

class RoundAdapter : RecyclerView.Adapter<RoundAdapter.ViewHolder>() {
    var listRounds = listOf<RoudModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRoundBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRounds[position])
    }
    
    override fun getItemCount() = listRounds.size
    
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRoundBinding.bind(view)
        
        fun bind(roundModel: RoudModel) {
            binding.tvLabel.isVisible = roundModel.teamOne.status == roundModel.teamTwo.status
            binding.roundView.teamOne = roundModel.teamOne
            binding.roundView.teamTwo = roundModel.teamTwo
        }
    }
}