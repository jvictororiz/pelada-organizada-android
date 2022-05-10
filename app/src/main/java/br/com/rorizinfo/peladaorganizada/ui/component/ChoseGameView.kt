package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.ChoseGameViewBinding
import br.com.rorizinfo.peladaorganizada.databinding.CountDownUpViewBinding

class ChoseGameView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    var onChangeTab: (()->Unit)? = null
    
    val binding by lazy {
        ChoseGameViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    
    var positionSelected: Int = 0
        get() = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.rb2 -> 1
            R.id.rb3 -> 2
            else -> 0
        }
        set(value) {
            if (value in 0..2) {
                field = value
                if (value == 0) binding.radioGroup.check(R.id.rb1)
                if (value == 1) binding.radioGroup.check(R.id.rb2)
                if (value == 2) binding.radioGroup.check(R.id.rb3)
            }
        }
}