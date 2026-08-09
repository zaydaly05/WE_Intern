enum OrderStatus {
  pending,
  processing,
  shipped,
  delivered,
  cancelled,
}

class Address {
  final String street;
  final String city;
  final String country;
  final String zipCode;

  const Address({
    required this.street,
    required this.city,
    required this.country,
    required this.zipCode,
  });

  factory Address.fromJson(Map<String, dynamic> json) {
    return Address(
      street: json['street'],
      city: json['city'],
      country: json['country'],
      zipCode: json['zipCode'],
    );
  }

  Map<String, dynamic> toJson() => {
        'street': street,
        'city': city,
        'country': country,
        'zipCode': zipCode,
      };
}

class Product {
  final int id;
  final String name;
  final double price;
  final int quantity;

  const Product({
    required this.id,
    required this.name,
    required this.price,
    required this.quantity,
  });

  double get totalPrice => price * quantity;

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'],
      name: json['name'],
      price: (json['price'] as num).toDouble(),
      quantity: json['quantity'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'price': price,
        'quantity': quantity,
      };
}

class Customer {
  final int id;
  final String name;
  final String? email;
  final String phone;
  final Address address;

  const Customer({
    required this.id,
    required this.name,
    this.email,
    required this.phone,
    required this.address,
  });

  factory Customer.fromJson(Map<String, dynamic> json) {
    return Customer(
      id: json['id'],
      name: json['name'],
      email: json['email'],
      phone: json['phone'],
      address: Address.fromJson(json['address']),
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'email': email,
        'phone': phone,
        'address': address.toJson(),
      };
}

class Order {
  final int id;
  final Customer customer;
  final List<Product> products;
  final DateTime orderDate;
  final OrderStatus status;
  final String? couponCode;

  const Order({
    required this.id,
    required this.customer,
    required this.products,
    required this.orderDate,
    this.status = OrderStatus.pending,
    this.couponCode,
  });

  double get totalAmount =>
      products.fold(0, (sum, item) => sum + item.totalPrice);

  factory Order.fromJson(Map<String, dynamic> json) {
    return Order(
      id: json['id'],
      customer: Customer.fromJson(json['customer']),
      products: (json['products'] as List)
          .map((e) => Product.fromJson(e))
          .toList(),
      orderDate: DateTime.parse(json['orderDate']),
      status: OrderStatus.values.firstWhere(
        (e) => e.name == json['status'],
      ),
      couponCode: json['couponCode'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'customer': customer.toJson(),
        'products': products.map((e) => e.toJson()).toList(),
        'orderDate': orderDate.toIso8601String(),
        'status': status.name,
        'couponCode': couponCode,
      };
}