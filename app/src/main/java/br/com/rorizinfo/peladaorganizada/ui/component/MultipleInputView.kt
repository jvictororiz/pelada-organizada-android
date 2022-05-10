package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.MultipleInputViewBinding

class MultipleInputView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    val binding by lazy {
        MultipleInputViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    var enableCount: Boolean = false
    var names: List<String> = listOf()
        set(value) {
            field = value
            if (enableCount) {
                binding.tvLabelRight.isVisible = true
                binding.tvLabelRight.text = context.getString(R.string.count_players_total, value.size.toString())
            } else {
                binding.tvLabelRight.isVisible = false
            }
        }
    
    var text: String = ""
        get() = binding.edtInput.text.toString()
        set(value) {
            field = value
            binding.edtInput.setText(value)
        }
    
    var hint: String = ""
        get() = binding.edtInput.hint.toString()
        set(value) {
            field = value
            binding.edtInput.hint = value
        }
    
    var labelLeft: String = ""
        set(value) {
            field = value
            binding.tvLabelLeft.text = value
        }
    
    init {
        configComponents()
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.MultipleInputView,
            defStyle,
            defStyleRes
        ).apply {
            with(this@MultipleInputView) {
                text = getString(R.styleable.MultipleInputView_android_text) ?: ""
                hint = getString(R.styleable.MultipleInputView_android_hint) ?: ""
                labelLeft = getString(R.styleable.MultipleInputView_labelLeft) ?: ""
                enableCount = getBoolean(R.styleable.MultipleInputView_enableCount, false)
            }
        }
    }
    
    private fun configComponents() {
        binding.edtInput.addTextChangedListener {
            names = it.toString()
                .trim()
                .split("\n")
                .filter { value -> value.trim().isNotEmpty() }
        }
    }
    
    fun clear() {
        text = ""
        names = listOf()
    }
}