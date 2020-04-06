package remapper.matcher.async

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine

fun <T> runBlocking(context: CoroutineContext, block: suspend () -> T): T =
    BlockingCoroutine<T>(context).also { block.startCoroutine(it) }.getValue()