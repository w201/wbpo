package one.codium.wbpo.network.data

import android.content.Context
import one.codium.wbpo.network.R

enum class InternalErrorCode {
    NO_CONNECTION, GENERAL_ERROR;

 fun getMessage(context: Context) =
     when (this) {
         NO_CONNECTION -> context.getString(R.string.general_network_noConnection)
         GENERAL_ERROR -> context.getString(R.string.general_network_error)
     }
}
