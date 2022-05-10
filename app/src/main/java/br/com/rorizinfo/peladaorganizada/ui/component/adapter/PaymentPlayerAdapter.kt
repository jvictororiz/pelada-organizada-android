package br.com.rorizinfo.peladaorganizada.ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.ItemPlayerPayBinding
import br.com.rorizinfo.peladaorganizada.domain.model.PlayerPay
import br.com.rorizinfo.peladaorganizada.ui.ext.dateToString
import java.sql.SQLOutput

class PaymentPlayerAdapter : RecyclerView.Adapter<PaymentPlayerAdapter.ViewHolder>() {
    
    var listPlayers = listOf<PlayerPay>()
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlayerPayBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPlayers[position])
    }
    
    override fun getItemCount(): Int {
        return listPlayers.size
    }
    
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPlayerPayBinding.bind(view)
        
        fun bind(player: PlayerPay) {
            binding.tvTitle.text = player.player.name
            if (player.timeExit != null) {
                binding.tvDescription.isVisible = true
                binding.tvDescription.text = view.context.getString(R.string.text_exit_player, player.timeExit?.dateToString("HH:mm"))
            } else {
                binding.tvDescription.isVisible = false
            }
            binding.body.setOnClickListener { binding.checkPay.isChecked = !binding.checkPay.isChecked }
            binding.checkPay.isChecked = player.paid
            binding.checkPay.setOnCheckedChangeListener { _, b ->
                player.paid = true
            }
            
        }
    }
}