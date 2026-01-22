package com.eazydeals.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Password utility class for secure password hashing and verification using BCrypt
 * Provides methods for password hashing and validation
 */
public class PasswordUtil {
    
    // BCrypt password encoder instance
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    
    /**
     * Hash a password using BCrypt algorithm
     * @param password The plain text password to hash
     * @return The hashed password
     */
    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return passwordEncoder.encode(password);
    }
    
    /**
     * Verify a password against a BCrypt hash
     * @param password The plain text password to verify
     * @param hashedPassword The BCrypt hash to verify against
     * @return true if password matches hash, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null) {
            return false;
        }
        try {
            return passwordEncoder.matches(password, hashedPassword);
        } catch (IllegalArgumentException e) {
            // Invalid hash format
            return false;
        }
    }
    
    /**
     * Check if a password is already hashed (BCrypt hashes start with $2a$, $2b$, or $2y$)
     * @param password The password to check
     * @return true if password appears to be BCrypt hashed
     */
    public static boolean isBCrypHashed(String password) {
        if (password == null) {
            return false;
        }
        return password.matches("^\\$2[aby]\\$\\d{2}\\$.{53}$");
    }
}
