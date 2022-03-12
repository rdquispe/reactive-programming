package observables

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


object ObservableCompositeDisposable {

    val LOG = LoggerFactory.getLogger(ObservableCompositeDisposable::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val compositeDisposable = CompositeDisposable()

        //Create an Single observer
        val disposableSingle: Disposable = Single.just("Hello World")
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

        //Create an observer
        val disposableMayBe: Disposable = Maybe.just("Hi")
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
        compositeDisposable.add(disposableSingle)
        compositeDisposable.add(disposableMayBe)

        //start observing
        compositeDisposable.dispose()
    }
}