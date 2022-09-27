package tsvetomir.tonchev.findit.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import tsvetomir.tonchev.findit.R

fun showLocationDialog(context: Context) {
    AlertDialog.Builder(context)
        .setTitle(R.string.dear_user_label)
        .setMessage(R.string.enable_location_msg)
        .setPositiveButton(R.string.settings) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            context.startActivity(intent)
        }
        .setNegativeButton(R.string.latter, null)
        .show()
}