package one.codium.wbpo.network

sealed class NetworkResult<out T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error(val error: Throwable) : NetworkResult<Nothing>()
}
