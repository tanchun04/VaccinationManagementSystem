package com.example.vaccinationmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnResetPassword;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);
    }

    private void setupClickListeners() {
        btnResetPassword.setOnClickListener(v -> resetPassword());
        
        tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void resetPassword() {
        String email = etEmail.getText().toString().trim();

        // Validation
        if (email.isEmpty()) {
            etEmail.setError("Vui lòng nhập email");
            etEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email không hợp lệ");
            etEmail.requestFocus();
            return;
        }

        // TODO: Gửi email reset password
        Toast.makeText(this, "Đã gửi link đặt lại mật khẩu đến email: " + email, 
            Toast.LENGTH_LONG).show();
        
        // Chuyển về màn hình đăng nhập
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}