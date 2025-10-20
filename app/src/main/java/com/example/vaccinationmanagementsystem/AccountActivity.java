package com.example.vaccinationmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etAddress, etDateOfBirth;
    private Button btnSaveProfile, btnChangePassword, btnLogout;
    private TextView tvUsername;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();
        loadUserProfile();
        setupClickListeners();
    }

    private void initViews() {
        tvUsername = findViewById(R.id.tvUsername);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogout = findViewById(R.id.btnLogout);
        
        sharedPreferences = getSharedPreferences("VaccinationPrefs", MODE_PRIVATE);
    }

    private void loadUserProfile() {
        // Load thông tin người dùng từ SharedPreferences hoặc database
        String username = sharedPreferences.getString("username", "admin");
        tvUsername.setText("Tài khoản: " + username);
        
        // Dữ liệu mẫu
        etFullName.setText("Nguyễn Văn A");
        etEmail.setText("nguyenvana@email.com");
        etPhone.setText("0123456789");
        etAddress.setText("123 Đường ABC, Quận 1, TP.HCM");
        etDateOfBirth.setText("01/01/1990");
    }

    private void setupClickListeners() {
        btnSaveProfile.setOnClickListener(v -> saveProfile());
        btnChangePassword.setOnClickListener(v -> changePassword());
        btnLogout.setOnClickListener(v -> logout());
    }

    private void saveProfile() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String dateOfBirth = etDateOfBirth.getText().toString().trim();

        if (fullName.isEmpty()) {
            etFullName.setError("Vui lòng nhập họ tên");
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Vui lòng nhập email");
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Vui lòng nhập số điện thoại");
            return;
        }

        // Lưu thông tin profile
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullName", fullName);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("address", address);
        editor.putString("dateOfBirth", dateOfBirth);
        editor.apply();

        Toast.makeText(this, "Đã lưu thông tin cá nhân", Toast.LENGTH_SHORT).show();
    }

    private void changePassword() {
        // Chuyển đến màn hình đổi mật khẩu
        Toast.makeText(this, "Chuyển đến đổi mật khẩu", Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        // Xóa thông tin đăng nhập
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Chuyển về màn hình đăng nhập
        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}