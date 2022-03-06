import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory

fun main() {

    val LOG = LoggerFactory.getLogger("HelloRxKotlin")

    val source: Observable<String> = Observable.create { e ->
        e.onNext("Hello")
        e.onNext("RxJava")
    }

    source.subscribe { e: String ->
        LOG.info("Observer 1 : {} Thread Name : {}", e, Thread.currentThread().name)
    }

    source.subscribe { e: String ->
        LOG.info("Observer 2 : {} Thread Name : {}", e, Thread.currentThread().name)
    }
}