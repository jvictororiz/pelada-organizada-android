package br.com.rorizinfo.peladaorganizada.ui.bottomSheet.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdNewPlayerModel(
    @Bindable var players: ObservableField<MutableList<String>> = ObservableField(mutableListOf()),
    var confirmOnSuccess: (() -> Unit)? = null
) : BaseObservable(), Parcelable