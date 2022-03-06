package observable_observer

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate
import org.slf4j.LoggerFactory


fun main() {

    val LOG = LoggerFactory.getLogger("ObservableAndObserver")

    val source: Observable<Int> = ObservableCreate<Int> { emitter ->
        try {
            emitter.onNext(10)
            emitter.onNext(11)
            emitter.onComplete()
        } catch (t: Throwable) {
            emitter.onError(t)
        }
    }

    val observer: Observer<Int> = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            LOG.info("Subscribed ")
        }

        override fun onNext(t: Int) {
            LOG.info("On Next : {}", t)
        }

        override fun onError(e: Throwable) {
            LOG.error("Error : {}", e.message)
            e.printStackTrace()
        }

        override fun onComplete() {
            LOG.info("Completed!")
        }
    }


    source.subscribe(observer)
}