🛒 E-Commerce Backend (Spring Boot)
📌 Project Description

This project is a backend system for an e-commerce platform built using Spring Boot. It provides RESTful APIs for user authentication, product management, cart operations, order processing, and payment integration using Stripe.

The system is designed with secure authentication, proper database relationships, and scalable architecture following industry best practices.

🚀 Features
🔐 Authentication & Authorization
User registration and login using JWT
Role-based access control (USER, ADMIN)
Secure password hashing using BCrypt
Stateless session management using Spring Security
📦 Product Management
Create, update, delete products (Admin only)
View all products
Stock validation before order placement
🛒 Cart Management
Add items to cart
Remove items from cart
Clear cart
Each user has a unique cart
Prevent duplicate cart creation
📑 Order Management
Create order from cart
Convert CartItems → OrderItems
View user-specific orders
Order contains product details and total price
Proper entity relationships (Order ↔ OrderItem)
💳 Payment Integration
Integrated with Stripe API
Create PaymentIntent for secure payments
Environment-based API key configuration
🛠️ Tech Stack
Java
Spring Boot
Spring Security
JWT Authentication
Hibernate (JPA)
PostgreSQL
Stripe API
Maven
⚙️ Setup Instructions
1️⃣ Clone Repository
git clone <your-repo-link>
cd ecommerce-backend
2️⃣ Configure Database

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Configure JWT Secret
jwt.secret=${JWT_SECRET:dev_secret}

Set environment variable:

Windows
setx JWT_SECRET "your_secure_jwt_key"
4️⃣ Configure Stripe Key
stripe.secret.key=${STRIPE_SECRET_KEY:sk_test_dummy}

Set environment variable:

setx STRIPE_SECRET_KEY "sk_test_your_secret_key"
5️⃣ Run Application
mvn spring-boot:run
📡 API Endpoints
🔐 Authentication
POST /api/auth/register → Register user
POST /api/auth/login → Login & get JWT
📦 Products
GET /api/products → Get all products
POST /api/products → Create product (ADMIN)
PUT /api/products/{id} → Update product (ADMIN)
DELETE /api/products/{id} → Delete product (ADMIN)
🛒 Cart
GET /api/cart → Get cart
POST /api/cart/add?productId=&qty= → Add item
DELETE /api/cart/remove/{id} → Remove item
DELETE /api/cart/clear → Clear cart
📑 Orders
POST /api/orders → Create order
GET /api/orders → Get user orders
GET /api/orders/{id} → Get order
💳 Payment
POST /api/payment/create-intent?amount= → Create payment
🔐 Security Highlights
JWT-based authentication
Role-based authorization (ADMIN / USER)
BCrypt password encryption
Environment-based secrets (JWT, DB, Stripe)
Stateless API security
✅ Improvements Implemented
✔ Fixed cart duplication issue (one cart per user)
✔ Implemented proper order creation flow
✔ Added stock validation
✔ Fixed entity relationships (Order ↔ OrderItem)
✔ Secured APIs with role-based authorization
✔ Removed hardcoded secrets
✔ Added global exception handling
🧠 Future Enhancements
Payment webhook handling (Stripe)
Order status tracking (PAID, SHIPPED, DELIVERED)
Pagination & filtering
Admin dashboard APIs
Logging & monitoring
🏁 Conclusion

This project demonstrates a complete backend system for an e-commerce platform with secure authentication, proper business logic, and scalable architecture using Spring Boot.
