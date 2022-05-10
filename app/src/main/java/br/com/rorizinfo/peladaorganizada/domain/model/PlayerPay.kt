package br.com.rorizinfo.peladaorganizada.domain.model

import java.util.*

data class PlayerPay(
    val player: Player,
    var paid: Boolean = false,
    var timeExit: Date? = null
)