package br.com.rorizinfo.peladaorganizada.domain.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField

data class Round(
    @Bindable var teamOne: Team,
    @Bindable var teamTwo: Team
): BaseObservable(){
}