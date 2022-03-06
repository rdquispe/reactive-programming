package connectable_observable

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


fun main() {

    val LOG = LoggerFactory.getLogger("ConectableObservable")

    val source: ConnectableObservable<Long> = Observable.interval(1, TimeUnit.SECONDS).publish()

    source.connect()

    source.subscribe { x: Long -> LOG.info("Subscribe 1: {}", x) }

    Thread.sleep(10000)

    source.subscribe { x: Long -> LOG.info("Subscribe 2: {}", x) }

    Thread.sleep(10000)
}