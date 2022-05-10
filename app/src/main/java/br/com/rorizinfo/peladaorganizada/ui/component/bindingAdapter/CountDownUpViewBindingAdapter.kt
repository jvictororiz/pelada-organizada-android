package br.com.rorizinfo.peladaorganizada.ui.component.bindingAdapter

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import br.com.rorizinfo.peladaorganizada.ui.component.CountDownUpView

class CountDownUpViewBindingAdapter {
    
    companion object {
        @JvmStatic
        @BindingAdapter("app:valueAttrChanged")
        fun setListener(view: CountDownUpView, listener: InverseBindingListener) {
            view.binding.tvValue.addTextChangedListener { listener.onChange() }
        }
        
        @JvmStatic
        @BindingAdapter("app:value", requireAll = false)
        fun setTextValue(view: CountDownUpView, value: Int?) {
            value?.let {
                view.changeCurrentValue(value)
            }
        }
        
        @JvmStatic
        @InverseBindingAdapter(attribute = "app:value")
        fun getTextValue(view: CountDownUpView): Int {
            return Integer.valueOf(view.binding.tvValue.text.toString())
        }
    }
}