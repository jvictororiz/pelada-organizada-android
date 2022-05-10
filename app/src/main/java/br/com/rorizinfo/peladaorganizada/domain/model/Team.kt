package br.com.rorizinfo.peladaorganizada.domain.model

import android.os.Parcelable
import br.com.rorizinfo.peladaorganizada.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val players: MutableList<Player> = mutableListOf(),
    var status: StatusGame = StatusGame.Neutral
) : Parcelable

@Parcelize
open class StatusGame(val color: Int) : Parcelable {
    object Win : StatusGame(R.color.primaryColor)
    object Lose : StatusGame(R.color.lose)
    object Neutral : StatusGame(R.color.gray)
}