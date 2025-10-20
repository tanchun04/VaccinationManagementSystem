package com.example.vaccinationmanagementsystem.utils;

import android.util.Patterns;
import android.widget.EditText;

public class ValidationUtils {

    // Validate email
    public static boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Validate phone number (Vietnamese format)
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        
        // Remove spaces and special characters
        String cleanPhone = phone.replaceAll("[\\s\\-\\(\\)]", "");
        
        // Check Vietnamese phone number patterns
        return cleanPhone.matches("^(\\+84|84|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-6|8|9]|9[0-4|6-9])[0-9]{7}$");
    }

    // Validate password strength
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        // Check for at least one letter and one number
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        
        return hasLetter && hasNumber;
    }

    // Validate full name
    public static boolean isValidFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return false;
        }
        
        // Check if contains at least 2 words and only letters and spaces
        String trimmedName = fullName.trim();
        return trimmedName.matches("^[a-zA-ZÀ-ỹ\\s]{2,}$") && trimmedName.contains(" ");
    }

    // Set error message for EditText
    public static void setError(EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
    }

    // Clear error for EditText
    public static void clearError(EditText editText) {
        editText.setError(null);
    }

    // Validate required field
    public static boolean isRequired(EditText editText, String fieldName) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            setError(editText, fieldName + " không được để trống");
            return false;
        }
        clearError(editText);
        return true;
    }

    // Validate email field
    public static boolean validateEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        
        if (email.isEmpty()) {
            setError(editText, "Email không được để trống");
            return false;
        }
        
        if (!isValidEmail(email)) {
            setError(editText, "Email không hợp lệ");
            return false;
        }
        
        clearError(editText);
        return true;
    }

    // Validate phone field
    public static boolean validatePhone(EditText editText) {
        String phone = editText.getText().toString().trim();
        
        if (phone.isEmpty()) {
            setError(editText, "Số điện thoại không được để trống");
            return false;
        }
        
        if (!isValidPhoneNumber(phone)) {
            setError(editText, "Số điện thoại không hợp lệ");
            return false;
        }
        
        clearError(editText);
        return true;
    }

    // Validate password field
    public static boolean validatePassword(EditText editText) {
        String password = editText.getText().toString();
        
        if (password.isEmpty()) {
            setError(editText, "Mật khẩu không được để trống");
            return false;
        }
        
        if (password.length() < 6) {
            setError(editText, "Mật khẩu phải có ít nhất 6 ký tự");
            return false;
        }
        
        if (!isValidPassword(password)) {
            setError(editText, "Mật khẩu phải chứa ít nhất 1 chữ cái và 1 số");
            return false;
        }
        
        clearError(editText);
        return true;
    }

    // Validate full name field
    public static boolean validateFullName(EditText editText) {
        String fullName = editText.getText().toString().trim();
        
        if (fullName.isEmpty()) {
            setError(editText, "Họ tên không được để trống");
            return false;
        }
        
        if (!isValidFullName(fullName)) {
            setError(editText, "Họ tên phải có ít nhất 2 từ và chỉ chứa chữ cái");
            return false;
        }
        
        clearError(editText);
        return true;
    }

    // Validate confirm password
    public static boolean validateConfirmPassword(EditText passwordEditText, EditText confirmPasswordEditText) {
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        
        if (confirmPassword.isEmpty()) {
            setError(confirmPasswordEditText, "Xác nhận mật khẩu không được để trống");
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            setError(confirmPasswordEditText, "Mật khẩu xác nhận không khớp");
            return false;
        }
        
        clearError(confirmPasswordEditText);
        return true;
    }
}