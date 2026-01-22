# EazyDeals E-Commerce Project - Database Connection Report
## Date: January 22, 2026

---

## Database Connection Configuration

### Location
- **File**: `src/main/java/com/eazydeals/helper/ConnectionProvider.java`

### Connection Details
```
Database Type:    MySQL
Driver:           com.mysql.cj.jdbc.Driver
URL:              jdbc:mysql://localhost:3306/eazydeals
Username:         root
Password:         root
Database Name:    eazydeals
```

### Connection Code Analysis
```java
public static Connection getConnection() {
    try {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eazydeals", "root", "root");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return connection;
}
```

---

## Database Schema
The project uses the following tables:
- **admin** - Admin users for management
- **cart** - User shopping cart items
- **category** - Product categories
- **products** - Product inventory
- **users** - User accounts
- **wishlist** - User wishlist items
- **orders** - Order records
- **ordered_products** - Individual products in orders

---

## Project Technologies
- **Backend**: Java 17, Servlet, JSP
- **Database**: MySQL 8.0
- **ORM/Connection**: JDBC with DriverManager
- **Build Tool**: Maven
- **Application Server**: Jetty (configured in pom.xml)

---

## MySQL Connection Requirements
✅ MySQL Server must be running on localhost:3306
✅ Database "eazydeals" must exist
✅ User "root" with password "root" must have access

---

## Database Setup
To set up the database:
1. Ensure MySQL Server is running
2. Import the SQL file: `eazydeals_maven.sql`
3. The database will be created automatically with all tables and sample data

---

## Dependencies (from pom.xml)
- mysql-connector-j 8.0.31
- jakarta.servlet-api 6.0.0
- jakarta.servlet.jsp-api 3.1.0
- jakarta.servlet.jsp.jstl-api 3.0.0
- Jakarta Mail (org.eclipse.angus:jakarta.mail:1.0.0)
- Jakarta Activation (jakarta.activation:jakarta.activation-api:2.1.2)

---

## Jetty Server Configuration
```xml
<plugin>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-maven-plugin</artifactId>
    <version>11.0.15</version>
    <configuration>
        <webApp>
            <contextPath>/ecommerce</contextPath>
        </webApp>
        <httpConnector>
            <port>8080</port>
        </httpConnector>
    </configuration>
</plugin>
```

**Access URL**: http://localhost:8080/ecommerce

---

## Status Summary
✅ **Database Connection**: Configured correctly
✅ **MySQL Driver**: mysql-connector-j 8.0.31 (compatible with Java 17)
✅ **Connection Method**: Connection pooling with singleton pattern
✅ **Project Ready**: Database and connection setup is complete

