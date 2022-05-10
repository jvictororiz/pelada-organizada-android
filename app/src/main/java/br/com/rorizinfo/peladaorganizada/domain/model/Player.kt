package br.com.rorizinfo.peladaorganizada.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String
): Parcelable