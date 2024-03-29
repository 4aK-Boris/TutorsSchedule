package dmitriy.losev.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> CoroutineScope.onDefaultAsync(call: suspend () -> T): Deferred<T> =
    async(Dispatchers.Default) { call() }

fun <T> CoroutineScope.asyncOnIO(call: suspend () -> T): Deferred<T> =
    async(Dispatchers.IO) { call() }

fun <T> CoroutineScope.launchOnIO(call: suspend () -> T) =
    launch(Dispatchers.IO) { call() }

suspend fun <T> switchOnMain(call: suspend () -> T) =
    withContext(context = Dispatchers.Main) { call() }
