package br.com.rorizinfo.peladaorganizada.ui.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import br.com.rorizinfo.peladaorganizada.R
import br.com.rorizinfo.peladaorganizada.databinding.CountDownUpViewBinding
import br.com.rorizinfo.peladaorganizada.databinding.LayoutToolbarBinding

class Toolbar @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributes, defStyle, defStyleRes) {
    
    private val binding by lazy {
        LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    var title: String = ""
        set(value) {
            field = value
            binding.tvTitle.text = value
        }
    
    @DrawableRes
    var iconRight: Drawable? = null
        get() = binding.imgRight.drawable
        set(value) {
            field = value
            if (value != null) {
                enableIconRight = true
                binding.imgRight.setImageDrawable(value)
            }
        }
    
    var enableButtonBack: Boolean = true
        get() = binding.imgBack.isVisible
        set(value) {
            field = value
            binding.imgBack.isVisible = value
        }
    
    var enableIconRight: Boolean = false
        get() = binding.imgRight.isVisible
        set(value) {
            field = value
            binding.imgRight.isVisible = value
        }
    
    init {
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.Toolbar,
            defStyle,
            defStyleRes
        ).apply {
            with(this@Toolbar) {
                title = getString(R.styleable.Toolbar_title) ?: ""
                enableIconRight = getBoolean(R.styleable.Toolbar_enableIconRight, enableIconRight)
                enableButtonBack =
                    getBoolean(R.styleable.Toolbar_enableBackButton, enableButtonBack)
                iconRight = getDrawable(R.styleable.Toolbar_iconRight)
            }
        }
    }
    
    fun setOnBackClickListener(event: (View) -> Unit) {
        binding.imgBack.setOnClickListener(event)
    }
    
    fun setOnRightClick(event: (View) -> Unit) {
        binding.imgRight.setOnClickListener(event)
    }
}