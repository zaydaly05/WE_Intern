package com.example.dartconversion
import java.time.LocalDateTime
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dartconversion.ui.theme.DartConversionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DartConversionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OrderScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OrderScreen(modifier: Modifier = Modifier) {

    val address = Address(
        street = "123 Main Street",
        city = "Cairo",
        country = "Egypt",
        zipCode = "11728"
    )

    val customer = Customer(
        id = 1,
        name = "Zayd Ali",
        email = "zayd@example.com",
        phone = "01012345678",
        address = address
    )

    val products = listOf(
        Product(101, "Laptop", 1200.0, 1),
        Product(102, "Mouse", 25.0, 2),
        Product(103, "Keyboard", 50.0, 1)
    )

    val orderMap: Map<String, Any> = mapOf(
        "id" to 5001,
        "customer" to customer,
        "products" to products,
        "orderDate" to LocalDateTime.now().toString(),
        "status" to "processing",
        "couponCode" to "SAVE10"
    )

    val order = Order.fromMap(orderMap)

    Text(
        text = """
            Order ID: ${order.id}
            
            Customer: ${order.customer.name}
            
            Status: ${order.status}
            
            Total: $${order.totalAmount}
        """.trimIndent(),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable

fun OrderPreview() {
    DartConversionTheme {
        OrderScreen()
    }
}