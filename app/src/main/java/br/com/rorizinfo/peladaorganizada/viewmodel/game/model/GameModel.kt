package br.com.rorizinfo.peladaorganizada.viewmodel.game.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import br.com.rorizinfo.peladaorganizada.domain.model.Game

data class GameModel(
    @Bindable var checkedTie: ObservableField<Boolean> = ObservableField(false),
    @Bindable var game: ObservableField<Game> = ObservableField(),
    @Bindable var newPlayers: ObservableField<MutableList<String>> = ObservableField()
): BaseObservable()