package dmitriy.losev.core.core.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun <T : Any, R : Any, K : Any> combine(
    flow1: Flow<T>,
    flow2: Flow<R>,
    transform: (T, R) -> K
): Flow<K> {
    return flow1.combine(flow2, transform = transform)
}

fun <T : Any, R : Any, S : Any, K : Any> combine(
    flow1: Flow<T>,
    flow2: Flow<R>,
    flow3: Flow<S>,
    transform: (T, R, S) -> K
): Flow<K> {
    val flow = combine(flow1, flow2) { value1, value2 ->
        Data2(value1, value2)
    }
    return combine(flow, flow3) { (value1, value2), value3 ->
        transform(value1, value2, value3)
    }
}

fun <T : Any, R : Any, S : Any, D : Any, K : Any> combine(
    flow1: Flow<T>,
    flow2: Flow<R>,
    flow3: Flow<S>,
    flow4: Flow<D>,
    transform: (T, R, S, D) -> K
): Flow<K> {
    val flow = combine(flow1, flow2, flow3) { value1, value2, value3 ->
        Data3(value1, value2, value3)
    }
    return combine(flow, flow4) { (value1, value2, value3), value4 ->
        transform(value1, value2, value3, value4)
    }
}