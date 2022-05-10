package br.com.rorizinfo.peladaorganizada.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.BottomSheetNewPlayersBinding
import br.com.rorizinfo.peladaorganizada.ui.bottomSheet.model.AdNewPlayerModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewPlayersBottomSheet : BottomSheetDialogFragment() {
    
    private lateinit var binding: BottomSheetNewPlayersBinding
    
    var model = AdNewPlayerModel()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bottom_sheet_new_players,
            null,
            false
        )
        binding.entitie = model
        return binding.root
    }
    
    override fun getTheme(): Int {
        return R.style.SheetDialog
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            dismiss()
            model.confirmOnSuccess?.invoke()
            binding.inputView.clear()
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