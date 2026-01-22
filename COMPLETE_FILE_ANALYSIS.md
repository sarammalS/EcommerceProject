# EazyDeals Project - Complete File Structure Analysis
**Date**: January 22, 2026

---

## PROJECT DIRECTORY STRUCTURE

### Backend Java Classes

#### 🔹 Servlets (12 files - HTTP Request Handlers)
Located: `src/main/java/com/eazydeals/servlets/`

1. **AddOperationServlet.java** - Add category/product operations
2. **AddToCartServlet.java** - Add items to shopping cart
3. **AdminServlet.java** - Admin panel operations
4. **CartOperationServlet.java** - Cart management (update, delete items)
5. **ChangePasswordServlet.java** - User password change
6. **LoginServlet.java** ✅ - User authentication login
7. **LogoutServlet.java** ✅ - User session logout
8. **OrderOperationServlet.java** - Order management
9. **RegisterServlet.java** ✅ - User registration
10. **UpdateOrderServlet.java** - Order status updates
11. **UpdateUserServlet.java** - User profile updates
12. **WishlistServlet.java** - Wishlist management

**Missing Servlets (referenced in web.xml but not implemented)**:
- ❌ AddressServlet
- ❌ AdminLoginServlet
- ❌ RemoveAddressServlet

#### 🔹 Data Access Objects (8 files - Database Layer)
Located: `src/main/java/com/eazydeals/dao/`

1. **AdminDao.java** - Admin CRUD operations
2. **CartDao.java** - Shopping cart operations
3. **CategoryDao.java** - Category management
4. **OrderDao.java** - Order operations
5. **OrderedProductDao.java** - Ordered items management
6. **ProductDao.java** - Product management
7. **UserDao.java** - User account operations
8. **WishlistDao.java** - Wishlist operations

#### 🔹 Entity Classes (9 files - Data Models)
Located: `src/main/java/com/eazydeals/entities/`

1. **Admin.java** - Admin user entity
2. **Cart.java** - Shopping cart entity
3. **Category.java** - Product category entity
4. **Message.java** - Message/notification entity
5. **Order.java** - Order entity
6. **OrderedProduct.java** - Ordered product item
7. **Product.java** - Product entity
8. **User.java** - User account entity
9. **Wishlist.java** - Wishlist item entity

#### 🔹 Helper/Utility Classes (4 files)
Located: `src/main/java/com/eazydeals/helper/`

1. **ConnectionProvider.java** - Database connection management
   - Uses: `com.mysql.cj.jdbc.Driver`
   - Connection: `jdbc:mysql://localhost:3306/eazydeals`
   - Credentials: root/root

2. **Mail.java** - Email utility class
   - Likely uses Jakarta Mail for sending emails

3. **MailMessenger.java** - Email messaging service
   - Handles order confirmations, registration emails, OTP

4. **OrderIdGenerator.java** - Unique order ID generation

---

## Frontend Files (JSP Pages & Assets)

### JSP Pages (22 files)
Located: `src/main/webapp/`

**Authentication Pages**:
- login.jsp - User login
- register.jsp - User registration
- adminlogin.jsp - Admin login
- forgot_password.jsp - Password recovery
- otp_code.jsp - OTP verification

**User Pages**:
- index.jsp - Home page
- products.jsp - Product listing
- viewProduct.jsp - Product details
- profile.jsp - User profile
- personalInfo.jsp - User information
- cart.jsp - Shopping cart
- wishlist.jsp - Wishlist
- checkout.jsp - Order checkout
- change_password.jsp - Password change

**Admin Pages**:
- admin.jsp - Admin dashboard
- display_products.jsp - Product management
- display_category.jsp - Category management
- display_orders.jsp - Order management
- display_users.jsp - User management
- display_admin.jsp - Admin management
- update_product.jsp - Edit products
- update_category.jsp - Edit categories

**Utility Pages**:
- error_page.jsp - 404 error
- error_exception.jsp - Exception display
- order.jsp - Order confirmation

### CSS Folder
- Bootstrap and custom stylesheets

### JavaScript Folder
- jQuery scripts and custom JavaScript

### Images Folder
- Product images and UI assets

### Components Folder
- Reusable JSP components

---

## Configuration Files

### web.xml (Web Application Deployment Descriptor)
- Declares all servlets and mappings
- 14 servlet mappings (3 are missing implementations)
- Error page configuration (404 → error_page.jsp)
- Welcome files configuration

### pom.xml (Maven Project Configuration)
```
GroupId: com.eazydeals
ArtifactId: e-commerce-website
Version: 1.0-SNAPSHOT
Packaging: war
Java Version: 17
```

**Key Dependencies**:
- mysql-connector-j:8.0.31
- jakarta.servlet-api:6.0.0
- jakarta.servlet.jsp-api:3.1.0
- jakarta.servlet.jsp.jstl-api:3.0.0
- org.eclipse.angus:jakarta.mail:1.0.0
- jakarta.activation:jakarta.activation-api:2.1.2

---

## Database Configuration

### Database File
- **eazydeals_maven.sql** - Complete database schema with sample data

### Connection Details
- **Host**: localhost:3306
- **Database**: eazydeals
- **Username**: root
- **Password**: root
- **Driver**: com.mysql.cj.jdbc.Driver

### Database Tables (8 tables)
1. admin - Administrator accounts
2. cart - Shopping cart items
3. category - Product categories
4. product - Product inventory
5. user - User accounts
6. wishlist - User wishlists
7. order - Orders placed
8. ordered_product - Products in orders

---

## File Count Summary

| Category | Count | Status |
|----------|-------|--------|
| Servlets | 12 | ✅ All implemented |
| DAOs | 8 | ✅ All implemented |
| Entities | 9 | ✅ All implemented |
| Helpers | 4 | ✅ All implemented |
| JSP Pages | 22 | ✅ All present |
| **TOTAL Java Classes** | **33** | ✅ Compiled successfully |

---

## Issues Identified

### 🔴 Critical Issues
1. **Missing Servlet Implementations**
   - AddressServlet
   - AdminLoginServlet
   - RemoveAddressServlet
   
   These are referenced in `web.xml` but don't have corresponding `.java` files

### 🟡 Configuration Issues
1. **Duplicate JSTL versions** in classpath
2. **Port 8080 in use** (from previous run)
3. **Hard-coded database credentials** in ConnectionProvider.java

### 🟢 What's Working
✅ Maven builds successfully
✅ All 33 Java source files compile
✅ Database schema and connection configured
✅ All JSP pages present
✅ Email configuration ready
✅ Security features (password change, OTP)

---

## How Each Component Works

### 1. User Registration Flow
- **register.jsp** → **RegisterServlet** → **UserDao** → **User entity** → MySQL

### 2. Product Browsing
- **products.jsp** → **ProductDao** → **Product entity** → Database

### 3. Shopping Cart
- **AddToCartServlet** → **CartDao** → **Cart entity** → Database
- **CartOperationServlet** → Update/Delete operations

### 4. Order Checkout
- **checkout.jsp** → **OrderOperationServlet** → **OrderDao** → **Order entity**
- **MailMessenger** sends confirmation email

### 5. Admin Management
- **admin.jsp** → **AdminServlet** → **AdminDao** → Database operations

---

## Recommended Actions

### Immediate (To Fix Runtime Issues)
1. Remove missing servlet mappings from web.xml OR create the servlet classes
2. Kill process on port 8080 or change port
3. Clean and rebuild project

### Short-term (Code Quality)
1. Consolidate JSTL library versions
2. Move database credentials to external config file
3. Add logging for debugging

### Long-term (Production Ready)
1. Implement connection pooling
2. Add input validation and XSS protection
3. Implement proper exception handling
4. Add unit tests

---

## Testing Checklist

- [ ] User Registration
- [ ] User Login/Logout
- [ ] Product Search and Filtering
- [ ] Add to Cart
- [ ] Update Cart Quantities
- [ ] Wishlist Operations
- [ ] Order Checkout
- [ ] Email Notifications
- [ ] Admin Login
- [ ] Category Management
- [ ] Product Management
- [ ] Order Status Updates
- [ ] User Profile Updates
- [ ] Password Change
- [ ] OTP Verification

