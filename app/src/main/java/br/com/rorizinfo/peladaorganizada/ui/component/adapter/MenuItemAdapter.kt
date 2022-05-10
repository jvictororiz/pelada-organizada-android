package br.com.rorizinfo.peladaorganizada.ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.rorizinfo.peladaorganizada.databinding.ItemMenuBinding
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.model.MenuItemModel

class MenuItemAdapter : RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {
    
    var listMenu = listOf<MenuItemModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var dismissDialog: (() -> Unit)? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }
    
    override fun getItemCount() = listMenu.size
    
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMenuBinding.bind(view)
        
        fun bind(menu: MenuItemModel) {
            binding.tvTitle.text = menu.title
            binding.tvTitle.setTextColor(ContextCompat.getColor(view.context, menu.colorTitle))
            binding.tvDescription.isVisible = menu.description.isNotEmpty()
            binding.tvDescription.text = menu.description
            binding.body.setOnClickListener {
                dismissDialog?.invoke()
                menu.eventClick?.invoke()
            }
        }
    }
}