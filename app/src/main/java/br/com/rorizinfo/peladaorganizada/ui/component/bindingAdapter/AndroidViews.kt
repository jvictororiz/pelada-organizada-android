package br.com.rorizinfo.peladaorganizada.ui.component.bindingAdapter

import android.view.View
import android.widget.Button
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import br.com.rorizinfo.peladaorganizada.ui.component.CountDownUpView

class AndroidViews {
    
    companion object {
        @JvmStatic
        @BindingAdapter("enabledAttrChanged")
        fun setListener(view: View, listener: InverseBindingListener) {
            listener.onChange()
        }
        
        @JvmStatic
        @BindingAdapter("enabled", requireAll = false)
        fun setEnable(view: View, value: Boolean) {
            view.isEnabled = value
        }
        
        @JvmStatic
        @InverseBindingAdapter(attribute = "enabled")
        fun getEnable(view: View): Boolean {
            return view.isEnabled
        }
    
        @JvmStatic
        @BindingAdapter("app:visibility", requireAll = false)
        fun setVisibility(view: View, value: Boolean) {
            view.isVisible = value
        }
    }
}