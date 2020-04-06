package remapper.matcher.async

import java.util.concurrent.ForkJoinPool
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

open class Pool(val pool: ForkJoinPool) : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        PoolContinuation(pool, continuation.context.fold(continuation) { cont, element ->
            if(element != this@Pool && element is ContinuationInterceptor) {
                element.interceptContinuation(cont)
            } else {
                cont
            }
        })

    fun runParallel(block: suspend () -> Unit) {
        pool.execute { launch(this, block) }
    }
}