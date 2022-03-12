package operators

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory


//Using fromArray operator to create an Observable
object ObservableFromArray {

    val LOG = LoggerFactory.getLogger(ObservableFromArray::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val letters = arrayOf("a", "b", "c", "d", "e", "f", "g")
        val result = StringBuilder()
        val observable = Observable.fromArray(letters)
        observable
            .map { array -> array.map(String::uppercase) }
            .subscribe { letter -> result.append(letter) }

        LOG.info(result.toString())
    }
}