package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.util.Locale


object SubscribeOn {
    val LOG = LoggerFactory.getLogger(SubscribeOn::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
            .subscribeOn(Schedulers.computation())
            .map { element -> element.uppercase() }
            .doOnNext { e -> LOG.info(Thread.currentThread().name) }
            .observeOn(Schedulers.newThread())
            .filter { e: String -> e.startsWith("P") }
            .observeOn(Schedulers.io())
            .subscribe { e: String -> print(e) }
        Thread.sleep(6000)
    }

    @Throws(InterruptedException::class)
    fun print(element: String) {
        LOG.info("$element : Printed By : ${Thread.currentThread().name}")
    }
}
