package br.com.rorizinfo.peladaorganizada.ui.ext

import br.com.rorizinfo.peladaorganizada.domain.model.Player
import java.text.SimpleDateFormat
import java.util.*

const val SEPARATOR = "\n"

fun String.stringToList(): MutableList<String> {
    return split(SEPARATOR)
        .toMutableList()
        .filterNot { it.isEmpty() }.toMutableList()
}

fun  MutableList<Player>.copy(): MutableList<Player>{
    return this.map { it.copy() }.toMutableList()
}

fun Date.dateToString(format: String): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}