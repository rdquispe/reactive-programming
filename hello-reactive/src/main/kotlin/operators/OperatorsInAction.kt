package operators

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory


object OperatorsInAction {

    val LOG = LoggerFactory.getLogger("OperatorsInAction")

    @JvmStatic
    fun main(args: Array<String>) {
        val obs = Observable.just(
            Employee(101, "Alexa", 60000.0, 4.0),
            Employee(123, "Dhwanit", 94000.0, 4.7),
            Employee(236, "Mike", 65000.0, 4.0),
            Employee(155, "Ella", 85000.0, 4.4),
            Employee(443, "George", 50000.0, 3.6),
            Employee(127, "Shreeja", 85000.0, 4.5),
            Employee(509, "Daniel", 60000.0, 4.0),
            Employee(344, "Lucy", 94000.0, 4.7),
            Employee(509, "Harry", 75000.0, 4.3),
            Employee(344, "Emma", 55000.0, 3.7)
        )
        obs
            .filter { employee -> employee.rating > 4.0 }
            .sorted { e1: Employee, e2: Employee ->
                e2.rating.compareTo(e1.rating)
            }
            .map { e: Employee -> e.name }
            .take(4) //.toList()
            .subscribe { x: String -> LOG.info(x) }
        val expenses = listOf(200, 500, 300, 340, 129, 234, 999, 1030, 3400, 890, 996, 789)
        Observable.fromIterable(expenses)
            .reduce { a: Int, b: Int -> a + b }
            .subscribe { x: Int -> LOG.info("{}", x) }
    }
}