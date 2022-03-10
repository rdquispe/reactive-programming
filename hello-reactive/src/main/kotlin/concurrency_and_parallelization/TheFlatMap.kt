package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.time.LocalTime


object TheFlatMap {
    val LOG = LoggerFactory.getLogger(TheFlatMap::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .flatMap { element ->
                Observable.just(element)
                    .subscribeOn(Schedulers.computation())
                    .map { value -> compute(value) }
            }
            .subscribe { str -> LOG.info(str) }
        Thread.sleep(7000)
    }

    @Throws(InterruptedException::class)
    fun compute(element: String): String {
        return element + " : Printed By : " + Thread.currentThread().name + " at : " + LocalTime.now()
    }
}