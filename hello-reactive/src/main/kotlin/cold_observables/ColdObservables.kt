package cold_observables

import io.reactivex.rxjava3.core.Observable


fun main() {
    var list: MutableList<Int> = ArrayList()

    list.add(16)
    list.add(17)
    list.add(18)

    val source: Observable<Int> = Observable.fromIterable(list)

    source.subscribe(System.out::println)

    list = getData(list)

    source.subscribe(System.out::println)

}

fun getData(list: MutableList<Int>): MutableList<Int> {
    list.add(19)
    return list
}

