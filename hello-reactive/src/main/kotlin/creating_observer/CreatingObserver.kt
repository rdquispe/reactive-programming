package creating_observer

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory

fun main() {
    val LOG = LoggerFactory.getLogger("CreatingObserver")

    val source: Observable<String> = Observable.just("Orange", "Black", "Red")

    source.subscribe({ i -> LOG.info(i) }, Throwable::printStackTrace) { LOG.info("Completed!") }

    println()

    source.subscribe({ i -> LOG.info(i) }, Throwable::printStackTrace)

    println()

    source.subscribe { i -> LOG.info(i) }
}