package merging_concatening

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.GroupedObservable
import operators.Employee
import org.slf4j.LoggerFactory


object Grouping {

    val LOG = LoggerFactory.getLogger(merging_concatening.Grouping::class.java)

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
        obs.groupBy { e: Employee -> e.rating }
            .flatMapSingle { e: GroupedObservable<Double, Employee> -> e.toList() }
            .subscribe { employees: List<Employee> ->
                LOG.info(employees.toString())
            }
    }
}