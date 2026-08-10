package com.example.formalpullrequest.domain.model

data class CustomerService(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val iconType: ServiceIcon
)

enum class ServiceIcon {
    MOBILE,
    INTERNET,
    LANDLINE,
    TV,
    SUPPORT
}