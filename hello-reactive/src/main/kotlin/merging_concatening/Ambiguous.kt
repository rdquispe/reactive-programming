package merging_concatening

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory
import java.util.Arrays
import java.util.concurrent.TimeUnit


object Ambiguous {

    val LOG = LoggerFactory.getLogger(Ambiguous::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val ob1 = Observable.interval(1, TimeUnit.SECONDS).take(10)
            .map { e: Long -> "Ob 1 :$e" }
        val ob2 = Observable.interval(10, TimeUnit.MILLISECONDS).take(10)
            .map { e: Long -> "Ob 2 :$e" }
        Observable.amb(Arrays.asList(ob1, ob2))
            .subscribe { item -> LOG.info(item) }
        Thread.sleep(11000)
    }
}