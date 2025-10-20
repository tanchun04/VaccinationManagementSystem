package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FeatureStubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_stub);

        TextView tvTitle = findViewById(R.id.tvFeatureTitle);
        TextView tvSubtitle = findViewById(R.id.tvFeatureSubtitle);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        if (title == null || title.isEmpty()) {
            title = getString(R.string.app_name);
        }
        if (description == null || description.isEmpty()) {
            description = "Màn hình đang được xây dựng.";
        }

        tvTitle.setText(title);
        tvSubtitle.setText(description);
    }
}
