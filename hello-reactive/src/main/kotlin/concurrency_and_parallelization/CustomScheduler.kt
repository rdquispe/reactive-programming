package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors


object CustomScheduler {
    val LOG = LoggerFactory.getLogger(CustomScheduler::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val executor = Executors.newFixedThreadPool(20)
        val scheduler = Schedulers.from(executor)
        val src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .subscribeOn(scheduler)
            .doFinally { executor.shutdown() }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
    }

    @Throws(InterruptedException::class)
    fun compute(element: String) {
        Thread.sleep(1000)
        LOG.info("Computation Done By : {} - {}", Thread.currentThread().name, element)
    }
}