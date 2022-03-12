package observables

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


object ObservableCompletable {

    val LOG = LoggerFactory.getLogger(ObservableCompletable::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {

        //Create an observer
        val disposable: Disposable = Completable.complete()
            .delay(2, TimeUnit.SECONDS, Schedulers.io())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                public override fun onStart() {
                    LOG.info("Started!")
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