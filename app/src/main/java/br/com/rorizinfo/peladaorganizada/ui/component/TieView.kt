package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.TieViewBinding

class TieView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    val binding by lazy {
        TieViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    var onConfirmTie: ((Int) -> Unit)? = null
    
    init {
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.TeamView,
            defStyle,
            defStyleRes
        ).apply {
            configView()
        }
    }
    
    private fun configView() {
        with(binding) {
            imgClose.setOnClickListener { hide() }
            btnTie.setOnClickListener { expand() }
            btnConfirm.setOnClickListener {
            
            }
            checkTimeOne.setOnCheckedChangeListener { _, check ->
                checkTimeOne.isChecked = check
                btnConfirm.isEnabled = checkTimeOne.isChecked || checkTimeTwo.isChecked
                if (check)
                    checkTimeTwo.isChecked = false
            }
            
            checkTimeTwo.setOnCheckedChangeListener { _, check ->
                btnConfirm.isEnabled = checkTimeOne.isChecked || checkTimeTwo.isChecked
                if (check)
                    checkTimeOne.isChecked = false
            }
            
            btnConfirm.setOnClickListener {
                val teamSelected = if (checkTimeOne.isChecked) 1 else 2
                onConfirmTie?.invoke(teamSelected)
                hide()
            }
        }
        
    }
    
    private fun expand() {
        with(binding) {
            btnTie.isEnabled = false
            cardBody.isInvisible = true
            TransitionManager.beginDelayedTransition(root.rootView.rootView as ViewGroup)
            cardBody.isVisible = true
            checkTimeTwo.isChecked = false
            checkTimeOne.isChecked = false
        }
    }
    
    private fun hide() {
        with(binding) {
            btnTie.isEnabled = true
            cardBody.isInvisible = true
            TransitionManager.beginDelayedTransition(root.rootView.rootView as ViewGroup)
            cardBody.isVisible = false
        }
    }
    
}