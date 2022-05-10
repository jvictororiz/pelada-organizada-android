package br.com.rorizinfo.peladaorganizada.ui.component.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import br.com.rorizinfo.peladaorganizada.ui.component.ChoseGameView

class ChoseGameViewBindingAdapter {
    
    companion object {
        @JvmStatic
        @BindingAdapter("app:positionSelectedAttrChanged")
        fun setListener(view: ChoseGameView, listener: InverseBindingListener) {
            view.binding.radioGroup.setOnCheckedChangeListener { _, _ ->
                listener.onChange()
                view.onChangeTab?.invoke()
            }
        }
        
        @JvmStatic
        @BindingAdapter("app:positionSelected", requireAll = false)
        fun setTextValue(view: ChoseGameView, value: Int?) {
            value?.let {
                view.positionSelected = value
            }
        }
        
        @JvmStatic
        @InverseBindingAdapter(attribute = "app:positionSelected")
        fun getTextValue(view: ChoseGameView): Int {
            return view.positionSelected
        }
    }
}