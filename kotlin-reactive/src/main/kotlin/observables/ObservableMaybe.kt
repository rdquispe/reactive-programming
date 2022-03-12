package observables

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

object ObservableMaybe {
    val LOG = LoggerFactory.getLogger(ObservableMaybe::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        //Create an observer
        val disposable: Disposable = Maybe.just("Hello World")
            .delay(2, TimeUnit.SECONDS, Schedulers.io())
            .subscribeWith(object : DisposableMaybeObserver<String>() {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onSuccess(value: String) {
                    LOG.info(value)
                }

                override fun onComplete() {
                    LOG.info("Done!")
                }
            })
        Thread.sleep(3000)
        //start observing
        disposable.dispose()
    }
}