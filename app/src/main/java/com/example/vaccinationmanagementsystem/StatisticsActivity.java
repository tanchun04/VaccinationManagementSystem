package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalVaccinations, tvTotalAppointments, tvCompletedVaccinations, tvPendingAppointments;
    private CardView cardTotalVaccinations, cardTotalAppointments, cardCompletedVaccinations, cardPendingAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initViews();
        setupToolbar();
        loadStatistics();
    }

    private void initViews() {
        tvTotalVaccinations = findViewById(R.id.tvTotalVaccinations);
        tvTotalAppointments = findViewById(R.id.tvTotalAppointments);
        tvCompletedVaccinations = findViewById(R.id.tvCompletedVaccinations);
        tvPendingAppointments = findViewById(R.id.tvPendingAppointments);
        
        cardTotalVaccinations = findViewById(R.id.cardTotalVaccinations);
        cardTotalAppointments = findViewById(R.id.cardTotalAppointments);
        cardCompletedVaccinations = findViewById(R.id.cardCompletedVaccinations);
        cardPendingAppointments = findViewById(R.id.cardPendingAppointments);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thống kê tiêm chủng");
    }

    private void loadStatistics() {
        // TODO: Load real statistics from database
        // Tạm thời hiển thị dữ liệu mẫu
        tvTotalVaccinations.setText("1,234");
        tvTotalAppointments.setText("567");
        tvCompletedVaccinations.setText("1,100");
        tvPendingAppointments.setText("89");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}