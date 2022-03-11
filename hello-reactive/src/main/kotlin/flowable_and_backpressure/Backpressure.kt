package flowable_and_backpressure

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger


object Backpressure {

    val LOG = LoggerFactory.getLogger(Backpressure::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        Flowable.range(1, 1000000)
            .map { emitted: Int ->
                LOG.info("Produced item is : $emitted : ${Thread.currentThread().name}")
                emitted
            }
            .observeOn(Schedulers.io())
            .subscribe(object : Subscriber<Int> {
                var subscription: Subscription? = null
                var count = AtomicInteger(0)
                override fun onSubscribe(subscription: Subscription) {
                    this.subscription = subscription
                    LOG.info("Asking for 20 items")
                    subscription.request(20)
                }

                override fun onNext(t: Int) {
                    if (count.getAndIncrement() % 20 == 0) {
                        LOG.info("Asking for next 20 items ")
                        subscription !!.request(20)
                    }
                    LOG.info("The subscriber consumed : $t")
                    sleep(100)
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }

                override fun onComplete() {
                    LOG.info("Completed")
                }
            })

        sleep(100000000)
    }

    private fun sleep(milliseconds: Long) {
        try {
            Thread.sleep(milliseconds)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
