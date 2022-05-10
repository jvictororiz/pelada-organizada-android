package br.com.rorizinfo.peladaorganizada.ui.ext

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import br.com.rorizinfo.peladaorganizada.R


fun Activity.alertMessage(message: String, event: (() -> Unit)? = null) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.alert))
        .setMessage(message)
        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            event?.invoke()
            dialog.dismiss()
        }
        .create()
        .show()
}