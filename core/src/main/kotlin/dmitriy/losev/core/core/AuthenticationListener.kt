package dmitriy.losev.core.core

interface AuthenticationListener {

    suspend fun onSuccess()

    suspend fun onError()

    suspend fun onLoading()
}