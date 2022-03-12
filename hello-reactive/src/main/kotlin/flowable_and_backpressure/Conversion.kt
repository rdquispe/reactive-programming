package flowable_and_backpressure

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.text.ParseException


object Conversion {
    val LOG = LoggerFactory.getLogger(Conversion::class.java)

    @Throws(InterruptedException::class, ParseException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Flowable.range(1, 1000000)
            .toObservable()
            .observeOn(Schedulers.io())
            .subscribe { e: Int ->
                LOG.info(
                    "$e ${Thread.currentThread().name}"
                )
            }
        Thread.sleep(5000)
    }
}