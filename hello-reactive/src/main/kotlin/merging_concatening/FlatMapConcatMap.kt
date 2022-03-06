package merging_concatening

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory


object FlatMapConcatMap {

    val LOG = LoggerFactory.getLogger(FlatMapConcatMap::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val list = listOf("Hello", "Reactive", "Programming")
        Observable.fromIterable(list)
            .flatMap { element -> Observable.fromArray(element.toCharArray().toList()) }
            .subscribe { characters -> LOG.info("$characters") }
    }
}