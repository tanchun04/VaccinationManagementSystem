package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalVaccinations, tvCompletedVaccinations, tvPendingVaccinations;
    private TextView tvLastVaccination, tvNextVaccination, tvVaccinationRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initViews();
        loadStatistics();
    }

    private void initViews() {
        tvTotalVaccinations = findViewById(R.id.tvTotalVaccinations);
        tvCompletedVaccinations = findViewById(R.id.tvCompletedVaccinations);
        tvPendingVaccinations = findViewById(R.id.tvPendingVaccinations);
        tvLastVaccination = findViewById(R.id.tvLastVaccination);
        tvNextVaccination = findViewById(R.id.tvNextVaccination);
        tvVaccinationRate = findViewById(R.id.tvVaccinationRate);
    }

    private void loadStatistics() {
        // Dữ liệu thống kê mẫu
        int totalVaccinations = 5;
        int completedVaccinations = 3;
        int pendingVaccinations = 2;
        double vaccinationRate = (double) completedVaccinations / totalVaccinations * 100;

        tvTotalVaccinations.setText(String.valueOf(totalVaccinations));
        tvCompletedVaccinations.setText(String.valueOf(completedVaccinations));
        tvPendingVaccinations.setText(String.valueOf(pendingVaccinations));
        tvVaccinationRate.setText(String.format("%.1f%%", vaccinationRate));

        // Ngày tiêm gần nhất
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String lastVaccinationDate = "15/04/2024";
        tvLastVaccination.setText(lastVaccinationDate);

        // Ngày tiêm tiếp theo
        String nextVaccinationDate = "15/10/2024";
        tvNextVaccination.setText(nextVaccinationDate);
    }
}