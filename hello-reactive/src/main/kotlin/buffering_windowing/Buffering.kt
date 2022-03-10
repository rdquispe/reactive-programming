package buffering_windowing

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


object Buffering {
    val LOG = LoggerFactory.getLogger(Buffering::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
// TODO: example 1
//        Observable.range(1, 30)
//            .buffer(4, 7)
//            .subscribe { item -> LOG.info(item.toString()) }

// TODO: example 3
//        Observable.interval(500, TimeUnit.MILLISECONDS)
//            .buffer(1, TimeUnit.SECONDS, 2)
//            .subscribe { item -> LOG.info(item.toString()) }

        // TODO: example 3 using buffering
        val interval = Observable.interval(500, TimeUnit.MILLISECONDS)
        Observable.interval(1000, TimeUnit.MILLISECONDS)
            .buffer(interval)
            .subscribe { item -> LOG.info(item.toString()) }
        Thread.sleep(8000)
    }
}