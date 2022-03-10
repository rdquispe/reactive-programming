package buffering_windowing

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


object Throttling {
    val LOG = LoggerFactory.getLogger(Throttling::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val obs = Observable.create { emitter: ObservableEmitter<String> ->
            emitter.onNext("A")
            Thread.sleep(200)
            emitter.onNext("B")
            Thread.sleep(100)
            emitter.onNext("C")
            Thread.sleep(400)
            emitter.onNext("D")
            Thread.sleep(300)
            emitter.onNext("E")
            Thread.sleep(800)
            emitter.onNext("F")
            Thread.sleep(900)
            emitter.onNext("X")
            Thread.sleep(600)
            emitter.onNext("Y")
            Thread.sleep(1000)
            emitter.onNext("Z")
            emitter.onComplete()
        }
        obs
            .debounce(700, TimeUnit.MILLISECONDS)
            .subscribe(
                { item: String -> LOG.info("onNext: $item") },
                { obj: Throwable -> obj.printStackTrace() }
            ) { LOG.info("onComplete") }
    }
}