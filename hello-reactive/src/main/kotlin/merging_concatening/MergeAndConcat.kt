package merging_concatening

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


object MergeAndConcat {
    @JvmStatic
    fun main(args: Array<String>) {

//		val src1 = Observable.just("Ella","Alexa","Lily");
//		val src2 = Observable.just("Priya","Chloe");

        val src1 = Observable.interval(1, TimeUnit.SECONDS).map { e: Long -> "src1 : $e" }
        val src2 = Observable.interval(1, TimeUnit.SECONDS).map { e: Long -> "src2 : $e" }

//        Observable.merge(src1, src2)
//            .subscribe { e -> println("Received : "+ e)};
        src1.concatWith(src2)
            .subscribe { e: String -> println("Received : $e") }
        Thread.sleep(20000)
    }
}