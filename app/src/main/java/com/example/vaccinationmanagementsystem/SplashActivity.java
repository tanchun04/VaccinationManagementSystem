package com.example.vaccinationmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vaccinationmanagementsystem.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 seconds
    private ImageView ivLogo;
    private TextView tvAppName, tvLoading;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
        initSession();
        startAnimations();
        navigateToNextScreen();
    }

    private void initViews() {
        ivLogo = findViewById(R.id.ivLogo);
        tvAppName = findViewById(R.id.tvAppName);
        tvLoading = findViewById(R.id.tvLoading);
    }

    private void initSession() {
        sessionManager = new SessionManager(this);
    }

    private void startAnimations() {
        // Logo animation
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        ivLogo.startAnimation(logoAnimation);

        // App name animation
        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_animation);
        tvAppName.startAnimation(textAnimation);

        // Loading text animation
        Animation loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_animation);
        tvLoading.startAnimation(loadingAnimation);
    }

    private void navigateToNextScreen() {
        new Handler().postDelayed(() -> {
            Intent intent;
            if (sessionManager.isLoggedIn()) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}