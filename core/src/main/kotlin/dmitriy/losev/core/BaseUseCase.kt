package dmitriy.losev.core

import dmitriy.losev.core.result.Output2
import dmitriy.losev.core.result.Output3
import dmitriy.losev.core.result.Output4
import dmitriy.losev.core.result.Output4WithFourNullable
import dmitriy.losev.core.result.Output5
import dmitriy.losev.core.result.Output6
import dmitriy.losev.core.result.Output7WithFourNullable
import dmitriy.losev.core.result.Output8WithFourNullable
import dmitriy.losev.core.result.Output9WithFourNullable
import dmitriy.losev.exception.BaseException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

abstract class BaseUseCase {

    open val exceptions: Map<KClass<out Throwable>, BaseException> = emptyMap()

    suspend fun <T : Any> convertException(f: suspend () -> T): T {
        return try {
            f()
        } catch (e: Throwable) {
            throw exceptions[e::class] ?: e
        }
    }

    suspend fun <T : Any> convertExceptionWithNullable(f: suspend () -> T?): T? {
        return try {
            f()
        } catch (e: Throwable) {
            throw exceptions[e::class] ?: e
        }
    }

    suspend fun launchFun(
        f1: suspend () -> Unit,
        f2: suspend () -> Unit,
        f3: suspend () -> Unit = { },
        f4: suspend () -> Unit = { },
        f5: suspend () -> Unit = { },
        f6: suspend () -> Unit = { }
    ): Unit = coroutineScope {
        launch { f1() }
        launch { f2() }
        launch { f3() }
        launch { f4() }
        launch { f5() }
        launch { f6() }
    }

    suspend fun <T : Any, R : Any> launchTwoFun(
        f1: suspend () -> T,
        f2: suspend () -> R
    ): Output2<T, R> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val result1 = job1.await()
        val result2 = job2.await()
        Output2(result1, result2)
    }

    suspend fun <T : Any, R : Any, K : Any> launchThreeFun(
        f1: suspend () -> T,
        f2: suspend () -> R,
        f3: suspend () -> K
    ): Output3<T, R, K> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        Output3(result1, result2, result3)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any> launchFourFun(
        f1: suspend () -> T,
        f2: suspend () -> R,
        f3: suspend () -> K,
        f4: suspend () -> S
    ): Output4<T, R, K, S> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        Output4(result1, result2, result3, result4)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any> launchFourFunWithFourNullable(
        f1: suspend () -> T?,
        f2: suspend () -> R?,
        f3: suspend () -> K?,
        f4: suspend () -> S?
    ): Output4WithFourNullable<T, R, K, S> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        Output4WithFourNullable(result1, result2, result3, result4)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any, D : Any> launchFiveFun(
        f1: suspend () -> T,
        f2: suspend () -> R,
        f3: suspend () -> K,
        f4: suspend () -> S,
        f5: suspend () -> D
    ): Output5<T, R, K, S, D> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val job5 = async { f5() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        val result5 = job5.await()
        Output5(result1, result2, result3, result4, result5)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any, D : Any, F : Any> launchSixFun(
        f1: suspend () -> T,
        f2: suspend () -> R,
        f3: suspend () -> K,
        f4: suspend () -> S,
        f5: suspend () -> D,
        f6: suspend () -> F
    ): Output6<T, R, K, S, D, F> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val job5 = async { f5() }
        val job6 = async { f6() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        val result5 = job5.await()
        val result6 = job6.await()
        Output6(result1, result2, result3, result4, result5, result6)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any, D : Any, F : Any, H : Any> launchSevenFunWithFourNullable(
        f1: suspend () -> T?,
        f2: suspend () -> R?,
        f3: suspend () -> K?,
        f4: suspend () -> S?,
        f5: suspend () -> D,
        f6: suspend () -> F,
        f7: suspend () -> H
    ): Output7WithFourNullable<T, R, K, S, D, F, H> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val job5 = async { f5() }
        val job6 = async { f6() }
        val job7 = async { f7() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        val result5 = job5.await()
        val result6 = job6.await()
        val result7 = job7.await()
        Output7WithFourNullable(result1, result2, result3, result4, result5, result6, result7)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any, D : Any, F : Any, H : Any, P : Any> launchEightFunWithFourNullable(
        f1: suspend () -> T?,
        f2: suspend () -> R?,
        f3: suspend () -> K?,
        f4: suspend () -> S?,
        f5: suspend () -> D,
        f6: suspend () -> F,
        f7: suspend () -> H,
        f8: suspend () -> P
    ): Output8WithFourNullable<T, R, K, S, D, F, H, P> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val job5 = async { f5() }
        val job6 = async { f6() }
        val job7 = async { f7() }
        val job8 = async { f8() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        val result5 = job5.await()
        val result6 = job6.await()
        val result7 = job7.await()
        val result8 = job8.await()
        Output8WithFourNullable(result1, result2, result3, result4, result5, result6, result7, result8)
    }

    suspend fun <T : Any, R : Any, K : Any, S : Any, D : Any, F : Any, H : Any, P : Any, L : Any> launchNineFunWithFourNullable(
        f1: suspend () -> T?,
        f2: suspend () -> R?,
        f3: suspend () -> K?,
        f4: suspend () -> S?,
        f5: suspend () -> D,
        f6: suspend () -> F,
        f7: suspend () -> H,
        f8: suspend () -> P,
        f9: suspend () -> L
    ): Output9WithFourNullable<T, R, K, S, D, F, H, P, L> = coroutineScope {
        val job1 = async { f1() }
        val job2 = async { f2() }
        val job3 = async { f3() }
        val job4 = async { f4() }
        val job5 = async { f5() }
        val job6 = async { f6() }
        val job7 = async { f7() }
        val job8 = async { f8() }
        val job9 = async { f9() }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
        val result4 = job4.await()
        val result5 = job5.await()
        val result6 = job6.await()
        val result7 = job7.await()
        val result8 = job8.await()
        val result9 = job9.await()
        Output9WithFourNullable(result1, result2, result3, result4, result5, result6, result7, result8, result9)
    }
}
