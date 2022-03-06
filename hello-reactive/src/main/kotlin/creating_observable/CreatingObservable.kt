package creating_observable

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory


fun main() {
    val LOG = LoggerFactory.getLogger("CreatingObservable")

    //create()
    val source: Observable<Int> = Observable.create { emiter ->
        emiter.onNext(10)
        emiter.onNext(11)
        emiter.onNext(12)
        emiter.onComplete()
    }

    source.subscribe{ element -> LOG.info("source element : {}", element) }


    //just()
    val just: Observable<Int> = Observable.just(1, 2, 3)

    just.subscribe{ element -> LOG.info("just element : {}", element) }


    //fromIterable

    val list: MutableList<String> = mutableListOf("Ram", "Shyam")

    val fromIterable: Observable<String> = Observable.fromIterable(list)

    list.add("Rahin")

    fromIterable.subscribe{ element -> LOG.info("fromIterable element : {}", element) }
}