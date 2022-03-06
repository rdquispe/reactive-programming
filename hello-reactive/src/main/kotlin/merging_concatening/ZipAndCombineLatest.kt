package merging_concatening

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


object ZipAndCombineLatest {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src1 = Observable.interval(200, TimeUnit.MILLISECONDS).take(10)
        val src2 = Observable.interval(1, TimeUnit.SECONDS).take(10)

        Observable.zip(src1, src2) { e1: Long, e2: Long -> "Source 1 : $e1 Source 2: $e2" }
            .subscribe { x: String? -> println(x) }

//        Observable.combineLatest(src1, src2) { e1: Long, e2: Long -> "Source 1 : $e1 Source 2: $e2" }
//            .subscribe { x: String? -> println(x) }

        Thread.sleep(20000)
    }
}