# 🛒 E-Commerce Backend (Spring Boot)

## 📌 Project Overview

This project is a backend system for an e-commerce platform built using Spring Boot. It provides RESTful APIs for user authentication, product management, cart operations, order processing, and payment integration.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* JWT-based authentication
* Secure login & registration
* Role-based access control (USER, ADMIN)
* Password encryption using BCrypt

### 📦 Product Management

* Create, update, delete products (Admin only)
* View all products

### 🛒 Cart Management

* Add products to cart
* Remove products from cart
* Clear cart
* One cart per user (no duplication)

### 📑 Order Management

* Create order from cart
* View user-specific orders
* Automatic total price calculation
* Stock validation before order placement

### 💳 Payment Integration

* Stripe PaymentIntent integration
* Secure API key handling via environment variables

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* PostgreSQL
* Stripe API

---

## 📁 Project Structure

```plaintext id="1sq0qf"
src/
 └── main/
     ├── java/in/ecom/
     │   ├── controller/
     │   ├── service/
     │   ├── repository/
     │   ├── model/
     │   ├── security/
     │   └── exception/
     └── resources/
         └── application.properties
pom.xml
README.md
```

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash id="9o0t2y"
git clone <your-repo-link>
cd ecommerce-backend
```

---

### 2. Configure Database

```properties id="jkb7ra"
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 3. Configure Stripe (IMPORTANT)

```properties id="1l3ox5"
stripe.api.key=${STRIPE_SECRET_KEY}
```

Set environment variable:

```bash id="y8pprv"
setx STRIPE_SECRET_KEY "<your_secret_key>"
```

⚠️ Do NOT hardcode secret keys in code.

---

### 4. Run Application

```bash id="djb8fp"
mvn spring-boot:run
```

---

## 📡 API Endpoints

### 🔐 Authentication

* POST `/auth/register`
* POST `/auth/login`

### 📦 Products

* GET `/api/products`
* POST `/api/products` (ADMIN)
* PUT `/api/products/{id}` (ADMIN)
* DELETE `/api/products/{id}` (ADMIN)

### 🛒 Cart

* GET `/api/cart`
* POST `/api/cart/add?productId={id}&qty={qty}`
* DELETE `/api/cart/remove/{itemId}`
* DELETE `/api/cart/clear`

### 📑 Orders

* POST `/api/orders`
* GET `/api/orders`
* GET `/api/orders/{id}`

---

## 🧩 Implementation Details

### 🔗 Entity Relationships

* User → Orders (OneToMany)
* Cart → CartItems (OneToMany)
* Order → OrderItems (OneToMany)

```java id="r3ev36"
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Order> orders;
```

```java id="4whd7l"
@ManyToOne
@JoinColumn(name = "order_id")
private Order order;
```

---

### 🛒 Cart Logic

* Uses `getOrCreateCart()` to prevent duplicate carts
* Validates quantity before adding items

---

### 📑 Order Processing

* Converts cart items → order items
* Calculates total dynamically
* Validates stock before placing order
* Reduces stock after order creation
* Clears cart after successful order

---

### 💳 Payment Flow

1. Order is created
2. PaymentIntent generated using Stripe
3. Client secret returned
4. Payment completed on frontend

---

## 🔐 Security Practices

* JWT authentication
* BCrypt password encryption
* Role-based authorization
* Environment variables for secrets

---

## 🔄 Sample Workflow

1. User registers & logs in → receives JWT
2. Adds products to cart
3. Creates order
4. Payment initiated via Stripe
5. Order completed

---

## ⚠️ Important Notes

* Ensure Stripe API key is configured before running
* Do not expose secret keys in repository
* Use proper validation to avoid invalid inputs

---

## ✅ Improvements Implemented

* Fixed cart duplication issue
* Added stock validation
* Implemented transaction management (@Transactional)
* Improved error handling with global exception handler
* Secured endpoints using role-based authorization

---

## 📌 Author

Lawanya Zanjad

