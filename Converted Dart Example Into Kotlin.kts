import java.time.LocalDateTime

enum class OrderStatus {
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

data class Address(
    val street: String,
    val city: String,
    val country: String,
    val zipCode: String
)

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
) {
    val totalPrice: Double
        get() = price * quantity
}

data class Customer(
    val id: Int,
    val name: String,
    val email: String?,
    val phone: String,
    val address: Address
)

data class Order(
    val id: Int,
    val customer: Customer,
    val products: List<Product>,
    val orderDate: LocalDateTime,
    val status: OrderStatus = OrderStatus.PENDING,
    val couponCode: String?
) {

    val totalAmount: Double
        get() = products.sumOf { it.totalPrice }

    companion object {

        fun fromMap(map: Map<String, Any>): Order {

            return Order(
                id = map["id"] as Int,

                customer = map["customer"] as Customer,

                products = map["products"] as List<Product>,

                orderDate = LocalDateTime.parse(map["orderDate"] as String),

                status = OrderStatus.valueOf(
                    (map["status"] as String).uppercase()
                ),

                couponCode = map["couponCode"] as String?
            )
        }
    }
}