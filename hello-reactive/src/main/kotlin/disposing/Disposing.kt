package disposing

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


private val disp = CompositeDisposable()

fun main(args: Array<String>) {
    val LOG = LoggerFactory.getLogger("Disposing")

    val src: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    val d1: Disposable = src.subscribe { e -> LOG.info("Observer 1 : $e") }
    val d2: Disposable = src.subscribe { e -> LOG.info("Observer 2 : $e") }
    Thread.sleep(5000)
    disp.addAll(d1, d2)
    disp.dispose()
    Thread.sleep(10000)
}