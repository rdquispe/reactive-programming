package operators

import io.reactivex.rxjava3.core.Observable

//Using combineLatest operator to combine Observables
object ObservableTester {
    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = arrayOf(1, 2, 3, 4, 5, 6)
        val letters = arrayOf("a", "b", "c", "d", "e", "f", "g")

        val observable1 = Observable.fromArray(letters)
        val observable2 = Observable.fromArray(numbers)
        Observable.combineLatest(observable1, observable2) { a, b -> a to b }
            .subscribe { pair ->
                pair.first.forEach { print(it) }
                pair.second.forEach { print(it) }
            }
    }
}