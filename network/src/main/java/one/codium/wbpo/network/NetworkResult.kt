package one.codium.wbpo.network

import android.content.Context
import one.codium.wbpo.network.data.InternalErrorCode

sealed class NetworkResult<out T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(
        val error: Throwable? = null,
        val internalCode: InternalErrorCode? = null
    ) : NetworkResult<Nothing>() {
        fun getMessage(context: Context) =
            when {
                error!=null -> this.error.localizedMessage
                internalCode!=null -> this.internalCode.getMessage(context)
                else -> ""
            }
    }
}
