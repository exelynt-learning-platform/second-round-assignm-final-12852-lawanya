# 🛒 E-Commerce Backend (Spring Boot)

## 📌 Project Description

This project is a backend system for an e-commerce platform built using Spring Boot. It provides REST APIs for user authentication, product management, cart operations, order processing, and payment integration.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* User registration and login using JWT
* Role-based access control (USER, ADMIN)
* Secure password hashing using BCrypt

### 📦 Product Management

* Create, update, delete products (Admin only)
* View all products

### 🛒 Cart Management

* Add items to cart
* Remove items from cart
* Clear cart
* Each user has a unique cart (no duplication)

### 📑 Order Management

* Create order from cart
* View user-specific orders
* Automatic total price calculation
* Stock validation before order placement

### 💳 Payment Integration

* Integrated with Stripe PaymentIntent API
* Secure API key configuration using environment variables

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

```
src/main/java/in/ecom/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── security/
 └── exception/
```

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone <your-repo-link>
cd ecommerce-backend
```

### 2. Configure Database

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 3. Configure Stripe Key (IMPORTANT)

```properties
stripe.api.key=${STRIPE_SECRET_KEY}
```

Set environment variable:

```bash
setx STRIPE_SECRET_KEY "<your_secret_key>"
```

⚠️ Do NOT store real secret keys in the repository.

---

### 4. Run Application

```bash
mvn spring-boot:run
```

---

## 📡 API Endpoints

### 🔐 Auth

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
* DELETE `/api/cart/remove/{id}`
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

Example:

```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Order> orders;
```

---

### 🛒 Cart Logic

* Implemented `getOrCreateCart()` to avoid duplicate carts
* Items validated before adding (quantity > 0)

---

### 📑 Order Processing

* Converts cart items into order items
* Calculates total price dynamically
* Validates stock before placing order
* Reduces product stock after order creation
* Clears cart after successful order

---

### 💳 Payment Flow

1. User creates order
2. Stripe PaymentIntent is generated
3. Client secret returned
4. Payment completed on frontend

---

## 🔐 Security Practices

* JWT-based authentication
* BCrypt password encryption
* Role-based authorization
* Environment variables for sensitive data

---

## 🔄 Sample Workflow

1. User registers & logs in → receives JWT
2. Adds products to cart
3. Creates order
4. Payment initiated via Stripe
5. Order completed

---

## ⚠️ Important Notes

* Stripe API key must be configured before running
* Do not expose secret keys
* Proper validation prevents invalid inputs

---

## ✅ Improvements Implemented

* Fixed cart duplication issue
* Implemented proper order creation flow
* Added stock validation and management
* Added transaction management (`@Transactional`)
* Secured APIs with role-based authorization
* Added global exception handling

---

## 📌 Author

Lawanya Zanjad
