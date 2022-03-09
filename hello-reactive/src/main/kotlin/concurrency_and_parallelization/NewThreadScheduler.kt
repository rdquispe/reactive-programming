package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory


object NewThreadScheduler {
    val LOG = LoggerFactory.getLogger(NewThreadScheduler::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .subscribeOn(Schedulers.newThread())
        src.subscribe { element -> task(element) }
        Thread.sleep(6000)
        src.subscribe { element -> task(element) }
        src.subscribe { element -> task(element) }
        src.subscribe { element -> task(element) }
        src.subscribe { element -> task(element) }
        Thread.sleep(500000)
    }

    @Throws(InterruptedException::class)
    fun task(element: String) {
        Thread.sleep(1000)
        LOG.info("Computation Done By : {} - {}", Thread.currentThread().name, element)
    }
}