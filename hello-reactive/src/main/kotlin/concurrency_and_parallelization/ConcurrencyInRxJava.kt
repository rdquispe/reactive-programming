package concurrency_and_parallelization

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import org.slf4j.LoggerFactory


object ConcurrencyInRxJava {

    val LOG = LoggerFactory.getLogger(ConcurrencyInRxJava::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val source = Observable.create { e: ObservableEmitter<String> ->
            Thread {
                e.onNext("Hello")
                e.onNext("RxJava")
            }.start()
        }
        source
            .subscribe { e: String ->
                LOG.info("Observer 1 :$e Thread is :${Thread.currentThread().name}")
            }
        source.subscribe { e: String ->
                LOG.info("Observer 2 :$e Thread is :${Thread.currentThread().name}")
            }
    }
}