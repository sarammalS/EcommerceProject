package com.eazydeals.security;

import jakarta.servlet.http.HttpSession;
import com.eazydeals.entities.User;
import com.eazydeals.entities.Admin;

/**
 * Session manager for secure session handling
 * Manages user and admin sessions with timeout and security features
 */
public class SessionManager {
    
    // Session attribute keys
    public static final String USER_SESSION_KEY = "user";
    public static final String ADMIN_SESSION_KEY = "admin";
    public static final String SESSION_TIMESTAMP_KEY = "sessionTimestamp";
    public static final String SESSION_IP_KEY = "sessionIP";
    
    // Session timeout in milliseconds (30 minutes)
    private static final long SESSION_TIMEOUT = 30 * 60 * 1000;
    
    /**
     * Create a user session
     * @param session The HttpSession object
     * @param user The user object to store in session
     * @param clientIP The client IP address
     */
    public static void createUserSession(HttpSession session, User user, String clientIP) {
        if (session != null && user != null) {
            session.setAttribute(USER_SESSION_KEY, user);
            session.setAttribute(SESSION_TIMESTAMP_KEY, System.currentTimeMillis());
            session.setAttribute(SESSION_IP_KEY, clientIP);
            // Set session timeout to 30 minutes
            session.setMaxInactiveInterval(30 * 60);
        }
    }
    
    /**
     * Create an admin session
     * @param session The HttpSession object
     * @param admin The admin object to store in session
     * @param clientIP The client IP address
     */
    public static void createAdminSession(HttpSession session, Admin admin, String clientIP) {
        if (session != null && admin != null) {
            session.setAttribute(ADMIN_SESSION_KEY, admin);
            session.setAttribute(SESSION_TIMESTAMP_KEY, System.currentTimeMillis());
            session.setAttribute(SESSION_IP_KEY, clientIP);
            // Set session timeout to 30 minutes
            session.setMaxInactiveInterval(30 * 60);
        }
    }
    
    /**
     * Verify if user session is valid and not expired
     * @param session The HttpSession object
     * @return true if session is valid and user is logged in
     */
    public static boolean isUserSessionValid(HttpSession session) {
        if (session == null) {
            return false;
        }
        
        User user = (User) session.getAttribute(USER_SESSION_KEY);
        Long timestamp = (Long) session.getAttribute(SESSION_TIMESTAMP_KEY);
        
        if (user == null || timestamp == null) {
            return false;
        }
        
        // Check if session has timed out
        long elapsedTime = System.currentTimeMillis() - timestamp;
        if (elapsedTime > SESSION_TIMEOUT) {
            // Session expired, clear it
            session.invalidate();
            return false;
        }
        
        return true;
    }
    
    /**
     * Verify if admin session is valid and not expired
     * @param session The HttpSession object
     * @return true if session is valid and admin is logged in
     */
    public static boolean isAdminSessionValid(HttpSession session) {
        if (session == null) {
            return false;
        }
        
        Admin admin = (Admin) session.getAttribute(ADMIN_SESSION_KEY);
        Long timestamp = (Long) session.getAttribute(SESSION_TIMESTAMP_KEY);
        
        if (admin == null || timestamp == null) {
            return false;
        }
        
        // Check if session has timed out
        long elapsedTime = System.currentTimeMillis() - timestamp;
        if (elapsedTime > SESSION_TIMEOUT) {
            // Session expired, clear it
            session.invalidate();
            return false;
        }
        
        return true;
    }
    
    /**
     * Get the logged-in user from session
     * @param session The HttpSession object
     * @return The User object if session is valid, null otherwise
     */
    public static User getUserFromSession(HttpSession session) {
        if (isUserSessionValid(session)) {
            return (User) session.getAttribute(USER_SESSION_KEY);
        }
        return null;
    }
    
    /**
     * Get the logged-in admin from session
     * @param session The HttpSession object
     * @return The Admin object if session is valid, null otherwise
     */
    public static Admin getAdminFromSession(HttpSession session) {
        if (isAdminSessionValid(session)) {
            return (Admin) session.getAttribute(ADMIN_SESSION_KEY);
        }
        return null;
    }
    
    /**
     * Invalidate user session (logout)
     * @param session The HttpSession object
     */
    public static void invalidateUserSession(HttpSession session) {
        if (session != null) {
            session.removeAttribute(USER_SESSION_KEY);
            session.removeAttribute(SESSION_TIMESTAMP_KEY);
            session.removeAttribute(SESSION_IP_KEY);
            session.invalidate();
        }
    }
    
    /**
     * Invalidate admin session (logout)
     * @param session The HttpSession object
     */
    public static void invalidateAdminSession(HttpSession session) {
        if (session != null) {
            session.removeAttribute(ADMIN_SESSION_KEY);
            session.removeAttribute(SESSION_TIMESTAMP_KEY);
            session.removeAttribute(SESSION_IP_KEY);
            session.invalidate();
        }
    }
    
    /**
     * Get client IP address from request
     * @param request The HttpServletRequest object
     * @return The client IP address
     */
    public static String getClientIP(jakarta.servlet.http.HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }
    
    /**
     * Refresh session timestamp (update last activity time)
     * @param session The HttpSession object
     */
    public static void refreshSession(HttpSession session) {
        if (session != null && 
            (session.getAttribute(USER_SESSION_KEY) != null || 
             session.getAttribute(ADMIN_SESSION_KEY) != null)) {
            session.setAttribute(SESSION_TIMESTAMP_KEY, System.currentTimeMillis());
        }
    }
}
