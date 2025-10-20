package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CertificateActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCertificates;
    private CertificateAdapter certificateAdapter;
    private List<VaccinationRecord> vaccinationRecords;
    private TextView tvNoCertificates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        initViews();
        setupToolbar();
        setupRecyclerView();
        loadVaccinationRecords();
    }

    private void initViews() {
        recyclerViewCertificates = findViewById(R.id.recyclerViewCertificates);
        tvNoCertificates = findViewById(R.id.tvNoCertificates);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chứng nhận tiêm chủng");
    }

    private void setupRecyclerView() {
        vaccinationRecords = new ArrayList<>();
        certificateAdapter = new CertificateAdapter(vaccinationRecords);
        recyclerViewCertificates.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCertificates.setAdapter(certificateAdapter);
    }

    private void loadVaccinationRecords() {
        // TODO: Load from database
        // Tạm thời tạo dữ liệu mẫu
        vaccinationRecords.add(new VaccinationRecord(
            "COVID-19 (Pfizer)", 
            "15/10/2023", 
            "Bệnh viện Đa khoa Thành phố",
            "Mũi 1",
            "Dr. Nguyễn Văn A"
        ));
        
        vaccinationRecords.add(new VaccinationRecord(
            "COVID-19 (Pfizer)", 
            "05/11/2023", 
            "Bệnh viện Đa khoa Thành phố",
            "Mũi 2", 
            "Dr. Trần Thị B"
        ));

        vaccinationRecords.add(new VaccinationRecord(
            "Cúm mùa", 
            "20/12/2023", 
            "Trung tâm Y tế Quận 1",
            "Mũi 1",
            "Dr. Lê Văn C"
        ));

        if (vaccinationRecords.isEmpty()) {
            tvNoCertificates.setVisibility(android.view.View.VISIBLE);
            recyclerViewCertificates.setVisibility(android.view.View.GONE);
        } else {
            tvNoCertificates.setVisibility(android.view.View.GONE);
            recyclerViewCertificates.setVisibility(android.view.View.VISIBLE);
            certificateAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}