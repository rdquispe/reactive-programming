package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory


object SingleScheduler {
    val LOG = LoggerFactory.getLogger(SingleScheduler::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .subscribeOn(Schedulers.single())
        src.subscribe { element -> sensitiveTask(element) }
        src.subscribe { element -> sensitiveTask(element) }
        src.subscribe { element -> sensitiveTask(element) }
        src.subscribe { element -> sensitiveTask(element) }
        src.subscribe { element -> sensitiveTask(element) }
        src.subscribe { element -> sensitiveTask(element) }
        Thread.sleep(500000)
    }

    @Throws(InterruptedException::class)
    fun sensitiveTask(element: String) {
        Thread.sleep(1000)
        LOG.info("Computation Done By : {} - {}", Thread.currentThread().name, element)
    }
}