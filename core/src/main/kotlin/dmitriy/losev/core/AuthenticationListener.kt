package dmitriy.losev.core

interface AuthenticationListener {

    suspend fun onSuccess()

    suspend fun onError()

    suspend fun onLoading()
}