# EazyDeals Admin Login Details
**Last Updated**: January 22, 2026

---

## 🔐 ADMIN ACCOUNTS

### Admin Account #1 (Primary)
```
Name:     Anirudh Kumar
Email:    test@gmail.com
Password: abc123
Phone:    7755632012
```

### Admin Account #2 (Secondary)
```
Name:     Ashish Kumar
Email:    test34@gmail.com
Password: abc
Phone:    8565452152
```

---

## 🌐 HOW TO LOGIN

### Step 1: Access Admin Login Page
```
URL: http://localhost:8080/ecommerce/adminlogin.jsp
```

### Step 2: Enter Credentials
Use either of the admin accounts above:
- **Email Field**: `test@gmail.com`
- **Password Field**: `abc123`

### Step 3: Submit Login Form
Click the "Login" button or press Enter

---

## ✅ AFTER SUCCESSFUL LOGIN

You will be redirected to the Admin Dashboard: `http://localhost:8080/ecommerce/admin.jsp`

### Admin Dashboard Features:
1. **Product Management** - Add, edit, delete products
2. **Category Management** - Manage product categories
3. **Order Management** - View and process customer orders
4. **User Management** - View and manage user accounts
5. **Admin Management** - Add new admin accounts

---

## 📋 ADMIN PAGES & FUNCTIONALITY

| Page | URL | Function |
|------|-----|----------|
| Admin Dashboard | `/ecommerce/admin.jsp` | Main admin hub |
| Display Products | `/ecommerce/display_products.jsp` | View all products |
| Update Product | `/ecommerce/update_product.jsp` | Edit product details |
| Display Categories | `/ecommerce/display_category.jsp` | View all categories |
| Update Category | `/ecommerce/update_category.jsp` | Edit categories |
| Display Orders | `/ecommerce/display_orders.jsp` | View customer orders |
| Display Users | `/ecommerce/display_users.jsp` | View registered users |
| Display Admins | `/ecommerce/display_admin.jsp` | View admin accounts |

---

## 🔧 TECHNICAL DETAILS

### Login Flow
1. Admin accesses `/ecommerce/adminlogin.jsp`
2. Enters email and password
3. Form submitted to `/ecommerce/AdminLoginServlet`
4. AdminLoginServlet queries `admin` table in MySQL
5. Credentials validated against database
6. If valid: Session created, redirected to `/ecommerce/admin.jsp`
7. If invalid: Redirected back to login with error message

### Database Table Structure
```sql
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
```

---

## 🔒 SECURITY NOTES

### Current Implementation
- Passwords stored in plain text (not recommended for production)
- Session-based authentication
- Server-side validation

### Recommendations for Production
- ⚠️ Hash passwords using bcrypt or PBKDF2
- ⚠️ Implement HTTPS/SSL encryption
- ⚠️ Add CSRF token protection
- ⚠️ Implement rate limiting on login attempts
- ⚠️ Add two-factor authentication
- ⚠️ Session timeout after inactivity
- ⚠️ Log all admin activities

---

## 🧪 TESTING ADMIN LOGIN

### Test with Provided Credentials
```
Email:    test@gmail.com
Password: abc123
```

### Expected Results
✅ Login successful → Redirected to admin dashboard
❌ Invalid email → Error message displayed
❌ Wrong password → Error message displayed
❌ Session expired → Redirected to login

---

## 👤 ADD NEW ADMIN ACCOUNT

To add a new admin account, you can:

### Option 1: Using Admin Dashboard
1. Login with existing admin credentials
2. Navigate to "Display Admins" page
3. Click "Add New Admin" button
4. Fill in details:
   - Name
   - Email
   - Password
   - Phone
5. Submit form

### Option 2: Direct Database Insert
```sql
INSERT INTO admin (name, email, password, phone) 
VALUES ('Your Name', 'your.email@example.com', 'password', '9876543210');
```

---

## 🚨 TROUBLESHOOTING

### "Unable to find admin" Message
- ✅ Verify email is exactly: `test@gmail.com`
- ✅ Verify password is exactly: `abc123`
- ✅ Check that MySQL database is running
- ✅ Confirm `eazydeals` database and `admin` table exist

### "Database connection failed"
- ✅ Check MySQL server is running on localhost:3306
- ✅ Verify credentials in `ConnectionProvider.java`:
  - Username: root
  - Password: root
  - Database: eazydeals

### "Session Expired"
- ✅ Login again with valid credentials
- ✅ Clear browser cookies and cache
- ✅ Try in a new browser window

### "Cannot access admin pages"
- ✅ Make sure you're logged in first
- ✅ Check if admin session is still active
- ✅ Look for session timeout

---

## 📊 ADMIN CAPABILITIES

### Product Operations
- View all products with details and images
- Add new products with price, description, category
- Edit existing product information
- Delete products from inventory
- Update product stock quantities

### Category Operations
- View all product categories
- Add new categories with images
- Edit category names and images
- Delete categories

### Order Management
- View all customer orders
- See order details (customer, items, total)
- Update order status (Pending, Processing, Shipped, Delivered)
- Track order fulfillment

### User Management
- View all registered users
- See user profiles and contact information
- Track user registration dates
- Monitor user activity

### Admin Management
- View all admin accounts
- Add new admin users
- Edit admin information
- Manage admin permissions

---

## 🎯 QUICK START

1. **Start the Application**
   ```
   cd G:\E-Commerce-Website-master
   mvn jetty:run
   ```

2. **Wait for Server to Start**
   - Look for: "Started Server" message
   - Server will be ready at: http://localhost:8080/ecommerce

3. **Go to Admin Login**
   ```
   http://localhost:8080/ecommerce/adminlogin.jsp
   ```

4. **Enter Admin Credentials**
   - Email: `test@gmail.com`
   - Password: `abc123`

5. **Access Admin Dashboard**
   - You'll be redirected to: http://localhost:8080/ecommerce/admin.jsp

---

## 📞 SUPPORT

### Key Files to Check
- `AdminLoginServlet.java` - Login processing logic
- `AdminDao.java` - Database queries for admin
- `adminlogin.jsp` - Login form UI
- `admin.jsp` - Admin dashboard

### Database File
- `eazydeals_maven.sql` - Contains all admin data

### Configuration
- `ConnectionProvider.java` - Database connection settings

---

**Note**: These are development/test credentials. For production use, generate strong passwords and implement proper security measures.

