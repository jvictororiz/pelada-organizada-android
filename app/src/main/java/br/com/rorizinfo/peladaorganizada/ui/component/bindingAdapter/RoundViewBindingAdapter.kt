package br.com.rorizinfo.peladaorganizada.ui.component.bindingAdapter

import androidx.databinding.BindingAdapter
import br.com.rorizinfo.peladaorganizada.domain.model.Team
import br.com.rorizinfo.peladaorganizada.ui.component.RoundView

class RoundViewBindingAdapter {
    
    companion object {
        
        @JvmStatic
        @BindingAdapter("teamOne", requireAll = true)
        fun setTeamOne(view: RoundView, value: Team?) {
            value?.let {
                view.teamOne = value
            }
        }
        
        @JvmStatic
        @BindingAdapter("teamTwo", requireAll = true)
        fun setTeamTwo(view: RoundView, value: Team?) {
            value?.let {
                view.teamTwo = value
            }
        }
    }
}