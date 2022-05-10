package br.com.rorizinfo.peladaorganizada.ui.component.bindingAdapter

import androidx.databinding.BindingAdapter
import br.com.rorizinfo.peladaorganizada.domain.model.Team
import br.com.rorizinfo.peladaorganizada.ui.component.TeamView

class TeamViewBindingAdapter {
    
    companion object {
        
        @JvmStatic
        @BindingAdapter("app:team", requireAll = false)
        fun setTeam(view: TeamView, value: Team?) {
            value?.let {
               view.team = value
            }
        }
    }
}