package dmitriy.losev.core.core.result

data class Output4WithThreeNullable<T : Any, R : Any, K : Any, S : Any>(
    val result1: T?,
    val result2: R?,
    val result3: K?,
    val result4: S
)
