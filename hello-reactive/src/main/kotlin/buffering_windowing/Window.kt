package buffering_windowing

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

object Window {
    val LOG = LoggerFactory.getLogger(Window::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val interval = Observable.interval(500, TimeUnit.MILLISECONDS)
        Observable.interval(1000, TimeUnit.MILLISECONDS)
            .window(interval)
            .subscribe { item -> LOG.info(item.toString()) }
        Thread.sleep(8000)
    }
}