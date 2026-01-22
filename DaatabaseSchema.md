erDiagram

ADMIN {
    INT id PK
    VARCHAR name
    VARCHAR email UNIQUE
    VARCHAR password
    VARCHAR phone
    BOOLEAN is_active
    TIMESTAMP created_at
    TIMESTAMP updated_at
    TIMESTAMP last_login
}

USER {
    INT userid PK
    VARCHAR name
    VARCHAR email UNIQUE
    VARCHAR password
    VARCHAR phone UNIQUE
    VARCHAR gender
    TIMESTAMP registerdate
    VARCHAR address
    VARCHAR city
    VARCHAR pincode
    VARCHAR state
    BOOLEAN is_active
    BOOLEAN email_verified
    TIMESTAMP last_login
    TIMESTAMP updated_at
}

CATEGORY {
    INT cid PK
    VARCHAR name UNIQUE
    VARCHAR image
    BOOLEAN is_active
    TIMESTAMP created_at
    TIMESTAMP updated_at
}

PRODUCT {
    INT pid PK
    VARCHAR name
    VARCHAR description
    DECIMAL price
    INT quantity
    INT discount
    VARCHAR image
    INT cid FK
    BOOLEAN is_active
    TIMESTAMP created_at
    TIMESTAMP updated_at
}

ORDERS {
    INT id PK
    VARCHAR orderid UNIQUE
    VARCHAR status
    VARCHAR paymentType
    INT userId FK
    DECIMAL total_amount
    VARCHAR shipping_address
    TIMESTAMP date
    TIMESTAMP delivery_date
    TIMESTAMP updated_at
}

ORDERED_PRODUCT {
    INT oid PK
    VARCHAR name
    INT quantity
    DECIMAL price
    VARCHAR image
    INT orderid FK
    TIMESTAMP created_at
}

CART {
    INT id PK
    INT uid FK
    INT pid FK
    INT quantity
}

WISHLIST {
    INT idwishlist PK
    INT iduser FK
    INT idproduct FK
}

CATEGORY ||--o{ PRODUCT : "has"
USER ||--o{ ORDERS : "places"
ORDERS ||--o{ ORDERED_PRODUCT : "contains"
USER ||--o{ CART : "adds"
PRODUCT ||--o{ CART : "added_to"
USER ||--o{ WISHLIST : "creates"
PRODUCT ||--o{ WISHLIST : "saved_in"
