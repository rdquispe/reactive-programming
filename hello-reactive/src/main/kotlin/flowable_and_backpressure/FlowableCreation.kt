package flowable_and_backpressure

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import org.slf4j.LoggerFactory


object FlowableCreation {

    val LOG = LoggerFactory.getLogger(FlowableCreation::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Flowable.create({ emitter: FlowableEmitter<Any> ->
            (0..5000).forEach { index ->
                if (emitter.isCancelled) return@create
                emitter.onNext(index)
            }
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER)
            .observeOn(Schedulers.io())
            .subscribe { x: Any -> LOG.info("$x") }
        Thread.sleep(2000)
    }
}