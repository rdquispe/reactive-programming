package replaying_and_caching

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


object Caching {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src = Observable.interval(1, TimeUnit.SECONDS)
            .cache()
        src.subscribe { e: Long -> println("Observer 1 : $e") }
        Thread.sleep(5000)
        src.subscribe { e: Long -> println("Observer 2 : $e") }
        Thread.sleep(3000)
    }
}