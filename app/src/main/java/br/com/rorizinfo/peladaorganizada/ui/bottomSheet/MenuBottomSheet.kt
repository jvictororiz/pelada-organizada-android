package br.com.rorizinfo.peladaorganizada.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuAdapter
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.BottomSheetMenuBinding
import br.com.rorizinfo.peladaorganizada.databinding.BottomSheetNewPlayersBinding
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.MenuItemAdapter
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.model.MenuItemModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheet : BottomSheetDialogFragment() {
    
    private lateinit var binding: BottomSheetMenuBinding
    private val adapter by lazy { MenuItemAdapter() }
    
    var listMenu = listOf<MenuItemModel>()
    var namePlayer = ""
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetMenuBinding.inflate(
            layoutInflater,
            null,
            false
        )
        return binding.root
    }
    
    override fun getTheme(): Int {
        return R.style.SheetDialog
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (namePlayer.isNotEmpty()) {
            binding.tvNamePlayer.text = namePlayer
            binding.tvNamePlayer.isVisible = true
        } else {
            binding.tvNamePlayer.isVisible = false
        }
        binding.rvMenu.adapter = adapter
        adapter.listMenu = listMenu
        binding.rvMenu.post {
            adapter.dismissDialog = {
                listMenu = emptyList()
                namePlayer = ""
                dismiss()
            }
        }
    }
    
    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, tag)
    }
    
    override fun dismiss() {
        if (isAdded) {
            super.dismiss()
        }
    }
}