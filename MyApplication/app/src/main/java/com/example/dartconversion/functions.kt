package com.example.dartconversion

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

fun main() {

    // Create Address
    val address = Address(
        street = "123 Main Street",
        city = "Cairo",
        country = "Egypt",
        zipCode = "11728"
    )

    // Create Customer
    val customer = Customer(
        id = 1,
        name = "Zayd Ali",
        email = "zayd@example.com",
        phone = "01012345678",
        address = address
    )

    // Create Products
    val products = listOf(
        Product(
            id = 101,
            name = "Laptop",
            price = 1200.0,
            quantity = 1
        ),
        Product(
            id = 102,
            name = "Mouse",
            price = 25.0,
            quantity = 2
        ),
        Product(
            id = 103,
            name = "Keyboard",
            price = 50.0,
            quantity = 1
        )
    )

    // Create a Map
    val orderMap: Map<String, Any> = mapOf(
        "id" to 5001,
        "customer" to customer,
        "products" to products,
        "orderDate" to LocalDateTime.now().toString(),
        "status" to "processing",
        "couponCode" to "SAVE10"
    )

    // Convert Map to Order
    val order = Order.fromMap(orderMap)

    // Print Order Details
    println("========== ORDER DETAILS ==========")
    println("Order ID      : ${order.id}")
    println("Customer Name : ${order.customer.name}")
    println("Email         : ${order.customer.email}")
    println("Phone         : ${order.customer.phone}")
    println()

    println("Address:")
    println("${order.customer.address.street}")
    println("${order.customer.address.city}, ${order.customer.address.country}")
    println("ZIP: ${order.customer.address.zipCode}")
    println()

    println("Products:")
    order.products.forEach { product ->
        println(
            "${product.name} | Price: ${product.price} | Qty: ${product.quantity} | Total: ${product.totalPrice}"
        )
    }

    println()
    println("Order Date    : ${order.orderDate}")
    println("Status        : ${order.status}")
    println("Coupon Code   : ${order.couponCode}")
    println("Total Amount  : ${order.totalAmount}")
}