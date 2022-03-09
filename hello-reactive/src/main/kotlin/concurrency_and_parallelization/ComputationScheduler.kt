package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory


object ComputationScheduler {

    val LOG = LoggerFactory.getLogger(ComputationScheduler::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .subscribeOn(Schedulers.computation())
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        src.subscribe { element -> compute(element) }
        Thread.sleep(50000)
    }

    @Throws(InterruptedException::class)
    fun compute(element: String) {
        Thread.sleep(1000)
        LOG.info("Computation Done By : {} - {}", Thread.currentThread().name, element)
    }
}
