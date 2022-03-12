package observables

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

object ObservableSingle {

    val LOG = LoggerFactory.getLogger(ObservableSingle::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // Create the observable
        val testSingle: Single<String> = Single.just("Hello World")

        // Create an observer
        val disposable: Disposable = testSingle
            .delay(2, TimeUnit.SECONDS, Schedulers.io())
            .subscribeWith(
                object : DisposableSingleObserver<String>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onSuccess(value: String) {
                        LOG.info(value)
                    }
                })

        Thread.sleep(3000)

        // Start observing
        disposable.dispose()
    }
}