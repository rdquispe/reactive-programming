package operators

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory


//Using take operator to filter an Observable
object ObservableTake {
    val LOG = LoggerFactory.getLogger(ObservableTake::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val letters = arrayOf("a", "b", "c", "d", "e", "f", "g")
        val result = StringBuilder()
        val observable = Observable.fromArray(letters)
        observable
            .take(2)
            .map { array -> array.map(String::uppercase) }
            .subscribe { letter -> result.append(letter) }
        LOG.info(result.toString())
    }
}