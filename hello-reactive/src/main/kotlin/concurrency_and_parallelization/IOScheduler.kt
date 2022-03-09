package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory


object IOScheduler {
    val LOG = LoggerFactory.getLogger(IOScheduler::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .subscribeOn(Schedulers.io())
        src.subscribe { element -> ioOperation(element) }
        Thread.sleep(6000)
        src.subscribe { element -> ioOperation(element) }
        src.subscribe { element -> ioOperation(element) }
        src.subscribe { element -> ioOperation(element) }
        src.subscribe { element -> ioOperation(element) }
        Thread.sleep(500000)
    }

    @Throws(InterruptedException::class)
    fun ioOperation(element: String) {
        Thread.sleep(1000)
        LOG.info("Computation Done By : {} - {}", Thread.currentThread().name, element)
    }
}