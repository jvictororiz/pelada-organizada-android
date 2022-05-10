package br.com.rorizinfo.peladaorganizada.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.BottomSheetPaymentsBinding
import br.com.rorizinfo.peladaorganizada.domain.model.PlayerPay
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.MenuItemAdapter
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.PaymentPlayerAdapter
import br.com.rorizinfo.peladaorganizada.ui.component.adapter.model.MenuItemModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerPayBottomSheet : BottomSheetDialogFragment() {
    
    private lateinit var binding: BottomSheetPaymentsBinding
    private val adapter by lazy { PaymentPlayerAdapter() }
    
    var listPlayers = listOf<PlayerPay>()
    private var cancelableListener: ((List<PlayerPay>) -> Unit)? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetPaymentsBinding.inflate(
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
        binding.tvCount.text = getString(R.string.count_players_total, listPlayers.size.toString())
        binding.rvPlayers.adapter = adapter
        adapter.listPlayers = listPlayers
    }
    
    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, tag)
    }
    
    fun setOnCancelableListener(event: (List<PlayerPay>) -> Unit) {
        cancelableListener = event
    }
    
    override fun dismiss() {
        if (isAdded) {
            cancelableListener?.invoke(adapter.listPlayers)
            super.dismiss()
        }
    }
}