package operators

class Employee(var id: Int, var name: String, var salary: Double, var rating: Double) {

    override fun toString(): String {
        return "Employee [id=$id, name=$name, salary=$salary, rating=$rating]"
    }
}