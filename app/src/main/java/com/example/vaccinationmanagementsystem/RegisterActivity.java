package com.example.vaccinationmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vaccinationmanagementsystem.dao.UserDAO;
import com.example.vaccinationmanagementsystem.model.User;
import com.example.vaccinationmanagementsystem.utils.ValidationUtils;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initDatabase();
        setupClickListeners();
    }

    private void initViews() {
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);
    }

    private void initDatabase() {
        userDAO = new UserDAO(this);
    }

    private void setupClickListeners() {
        btnRegister.setOnClickListener(v -> attemptRegister());
        
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void attemptRegister() {
        // Validate all fields
        boolean isValid = true;
        
        isValid &= ValidationUtils.validateFullName(etFullName);
        isValid &= ValidationUtils.validateEmail(etEmail);
        isValid &= ValidationUtils.validatePhone(etPhone);
        isValid &= ValidationUtils.validatePassword(etPassword);
        isValid &= ValidationUtils.validateConfirmPassword(etPassword, etConfirmPassword);
        
        if (!isValid) {
            return;
        }

        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Kiểm tra email đã tồn tại
        if (userDAO.isEmailExists(email)) {
            etEmail.setError("Email đã được sử dụng");
            etEmail.requestFocus();
            return;
        }

        // Tạo user mới
        User newUser = new User(fullName, email, phone, password, "");
        long result = userDAO.createUser(newUser);
        
        if (result != -1) {
            Toast.makeText(this, "Đăng ký thành công!\nTên: " + fullName + 
                "\nEmail: " + email, Toast.LENGTH_LONG).show();
            
            // Chuyển về màn hình đăng nhập
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại! Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }
}