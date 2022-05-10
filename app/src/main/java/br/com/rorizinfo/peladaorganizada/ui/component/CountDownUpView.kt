package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.CountDownUpViewBinding

class CountDownUpView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    val binding by lazy {
        CountDownUpViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    
    var value: Int = DEFAULT_INITAL_VALUE
        set(value) {
            field = value
            binding.tvValue.text = value.toString()
        }
    
    var maxValue: Int = MAX_DEFAULT_VALUE
    var label: String = ""
        set(value) {
            field = value
            binding.tvLabelLeft.text = value
        }
    
    init {
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.CountDownUpView,
            defStyle,
            defStyleRes
        ).apply {
            with(this@CountDownUpView) {
                value = getInt(R.styleable.CountDownUpView_android_value, DEFAULT_INITAL_VALUE)
                maxValue = getInt(R.styleable.CountDownUpView_maxValue, maxValue)
                label = getString(R.styleable.CountDownUpView_label).toString()
            }
        }
        configComponents()
    }
    
    private fun configComponents() {
        binding.btnIncrement.setOnClickListener { changeCurrentValue(value + 1) }
        binding.btnDecrement.setOnClickListener { changeCurrentValue(value - 1) }
    }
    
    fun changeCurrentValue(currentValue: Int) {
        binding.btnDecrement.isEnabled = currentValue != 1
        binding.btnIncrement.isEnabled = currentValue != maxValue
        if (currentValue in 1..maxValue) {
            this.value = currentValue
        }
    }
    
    companion object {
        private const val DEFAULT_INITAL_VALUE = 5
        private const val MAX_DEFAULT_VALUE = 50
    }
}