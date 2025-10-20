package com.example.vaccinationmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import com.example.vaccinationmanagementsystem.model.User;
import com.example.vaccinationmanagementsystem.utils.SessionManager;

public class AccountActivity extends AppCompatActivity {

    private TextView tvUserName, tvUserEmail, tvUserPhone, tvUserAddress;
    private CardView cardProfile, cardSettings, cardHelp, cardAbout;
    private Button btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();
        initSession();
        setupToolbar();
        loadUserInfo();
        setupClickListeners();
    }

    private void initViews() {
        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserPhone = findViewById(R.id.tvUserPhone);
        tvUserAddress = findViewById(R.id.tvUserAddress);
        cardProfile = findViewById(R.id.cardProfile);
        cardSettings = findViewById(R.id.cardSettings);
        cardHelp = findViewById(R.id.cardHelp);
        cardAbout = findViewById(R.id.cardAbout);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void initSession() {
        sessionManager = new SessionManager(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quản lý tài khoản");
    }

    private void loadUserInfo() {
        User currentUser = sessionManager.getCurrentUser();
        if (currentUser != null) {
            tvUserName.setText(currentUser.getFullName());
            tvUserEmail.setText(currentUser.getEmail());
            tvUserPhone.setText(currentUser.getPhone());
            tvUserAddress.setText(currentUser.getAddress().isEmpty() ? "Chưa cập nhật" : currentUser.getAddress());
        }
    }

    private void setupClickListeners() {
        cardProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Chỉnh sửa thông tin cá nhân", Toast.LENGTH_SHORT).show();
            // TODO: Mở màn hình chỉnh sửa profile
        });

        cardSettings.setOnClickListener(v -> {
            Toast.makeText(this, "Cài đặt ứng dụng", Toast.LENGTH_SHORT).show();
            // TODO: Mở màn hình cài đặt
        });

        cardHelp.setOnClickListener(v -> {
            Toast.makeText(this, "Trợ giúp và hỗ trợ", Toast.LENGTH_SHORT).show();
            // TODO: Mở màn hình trợ giúp
        });

        cardAbout.setOnClickListener(v -> {
            showAboutDialog();
        });

        btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Về ứng dụng")
                .setMessage("Hệ thống quản lý tiêm chủng\nVersion 1.0\n\nỨng dụng giúp quản lý lịch tiêm chủng và theo dõi chứng nhận tiêm chủng một cách hiệu quả.")
                .setPositiveButton("Đóng", null)
                .show();
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                .setPositiveButton("Đăng xuất", (dialog, which) -> {
                    sessionManager.logoutUser();
                    Toast.makeText(AccountActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}