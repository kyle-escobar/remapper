package remapper.matcher.async

import java.util.concurrent.ForkJoinPool
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

class PoolContinuation<T>(val pool: ForkJoinPool, val continuation: Continuation<T>) : Continuation<T> {

    override val context: CoroutineContext = continuation.context

    override fun resumeWith(result: Result<T>) {
        pool.execute { continuation.resumeWith(result) }
    }
}