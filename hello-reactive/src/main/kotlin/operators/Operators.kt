package operators

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory


object Operators {
    val LOG = LoggerFactory.getLogger("Operators")

    @JvmStatic
    fun main(args: Array<String>) {
        Observable.just(60, 57, 89, 90, 23, 100, 98)
            .filter { element: Int -> element > 75 }
            .sorted()
            .subscribe { element: Int -> LOG.info("Grade A with $element") }
    }
}
