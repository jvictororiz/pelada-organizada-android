package br.com.rorizinfo.peladaorganizada.ui.component.adapter.model

import br.com.rorizinfo.peladaorganizada.R

data class MenuItemModel(
    val title: String = "",
    val description: String = "",
    val colorTitle: Int = R.color.primaryColor,
    val eventClick: (() -> Unit)? = null
)