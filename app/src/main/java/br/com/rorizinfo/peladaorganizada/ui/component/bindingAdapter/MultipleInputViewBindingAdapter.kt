package br.com.rorizinfo.peladaorganizada.ui.component.bindingAdapter

import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import br.com.rorizinfo.peladaorganizada.ui.component.MultipleInputView

class MultipleInputViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:namesAttrChanged")
        fun setListener(view: MultipleInputView, listener: InverseBindingListener) {
            view.binding.edtInput.addTextChangedListener {  listener.onChange() }
        }

        @JvmStatic
        @BindingAdapter("app:names", requireAll = false)
        fun setTextValue(view: MultipleInputView, names: List<String>?) {
            view.names = names?: listOf()
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "app:names")
        fun getTextValue(view: MultipleInputView): List<String> {
            return view.names
        }
    }
}