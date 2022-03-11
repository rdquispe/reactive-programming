package buffering_windowing

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit


object Switching {
    val LOG = LoggerFactory.getLogger(Switching::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val source = Observable.just("John", "Lily", "Emma", "Reyan", "Darshin")
            .concatMap { name ->
                Observable.just(name)
                    .delay(
                        ThreadLocalRandom.current().nextInt(1000).toLong(),
                        TimeUnit.MILLISECONDS
                    )
            }
        Observable.interval(2, TimeUnit.SECONDS)
            .switchMap { s: Long ->
                source.doOnDispose { LOG.info("Disposing and starting again!") }
            }
            .subscribe { x: String -> LOG.info(x) }
        Thread.sleep(10000)
    }
}