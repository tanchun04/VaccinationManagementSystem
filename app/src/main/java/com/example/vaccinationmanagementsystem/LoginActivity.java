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
import com.example.vaccinationmanagementsystem.utils.SessionManager;
import com.example.vaccinationmanagementsystem.utils.ValidationUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvRegister, tvForgotPassword;
    private UserDAO userDAO;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initDatabase();
        checkLoginStatus();
        setupClickListeners();
    }

    private void initViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
    }

    private void initDatabase() {
        userDAO = new UserDAO(this);
        sessionManager = new SessionManager(this);
    }

    private void checkLoginStatus() {
        if (sessionManager.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setupClickListeners() {
        btnLogin.setOnClickListener(v -> attemptLogin());

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validation
        boolean isValid = true;
        
        if (!ValidationUtils.isRequired(etUsername, "Tên đăng nhập")) {
            isValid = false;
        }
        
        if (!ValidationUtils.isRequired(etPassword, "Mật khẩu")) {
            isValid = false;
        }
        
        if (!isValid) {
            return;
        }

        // Thử đăng nhập với database
        User user = userDAO.loginUser(username, password);
        if (user != null) {
            // Lưu session
            sessionManager.createLoginSession(user);
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Fallback cho admin account
            if (username.equals("admin") && password.equals("123456")) {
                // Tạo user admin tạm thời
                User adminUser = new User();
                adminUser.setUserId(0);
                adminUser.setFullName("Administrator");
                adminUser.setEmail("admin");
                adminUser.setPhone("0000000000");
                adminUser.setAddress("System");
                
                sessionManager.createLoginSession(adminUser);
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}