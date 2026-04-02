# second-round-assignm-final-12852-lawanya
Final Project Assignment - This repository contains the complete final project code and documentation.
# 🛒 E-Commerce Backend (Spring Boot)

## 📌 Project Description

This project is a backend system for an e-commerce platform built using Spring Boot. It provides APIs for user authentication, product management, cart operations, order processing, and payment integration.

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
* Each user has a unique cart

### 📑 Order Management

* Create order from cart
* View user orders
* Order contains product details and total price

### 💳 Payment Integration

* Integrated with Stripe for payment processing
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

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone <your-repo-link>
cd ecommerce-backend
```

### 2. Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecomDB
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
```

---

### 3. Configure Stripe Key (IMPORTANT)

```properties
stripe.api.key=${STRIPE_SECRET_KEY}
```

Set environment variable:

```bash
setx STRIPE_SECRET_KEY "sk_test_your_secret_key"
```

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
* POST `/api/cart/add`
* DELETE `/api/cart/remove/{id}`
* DELETE `/api/cart/clear`

### 📑 Orders

* POST `/api/orders`
* GET `/api/orders`
* GET `/api/orders/{id}`

