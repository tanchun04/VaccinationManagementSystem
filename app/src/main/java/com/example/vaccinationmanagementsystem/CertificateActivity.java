package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CertificateActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCertificates;
    private CertificateAdapter certificateAdapter;
    private List<VaccinationRecord> vaccinationRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        initViews();
        setupRecyclerView();
        loadVaccinationRecords();
    }

    private void initViews() {
        recyclerViewCertificates = findViewById(R.id.recyclerViewCertificates);
    }

    private void setupRecyclerView() {
        recyclerViewCertificates.setLayoutManager(new LinearLayoutManager(this));
        certificateAdapter = new CertificateAdapter(vaccinationRecords);
        recyclerViewCertificates.setAdapter(certificateAdapter);
    }

    private void loadVaccinationRecords() {
        // Dữ liệu mẫu
        vaccinationRecords = new ArrayList<>();
        vaccinationRecords.add(new VaccinationRecord(
                "COVID-19 Vaccine",
                "Pfizer-BioNTech",
                "15/03/2024",
                "Bệnh viện Chợ Rẫy",
                "Lô: PF123456",
                "Đã hoàn thành"
        ));
        vaccinationRecords.add(new VaccinationRecord(
                "COVID-19 Vaccine",
                "Pfizer-BioNTech",
                "15/04/2024",
                "Bệnh viện Chợ Rẫy",
                "Lô: PF123457",
                "Đã hoàn thành"
        ));
        vaccinationRecords.add(new VaccinationRecord(
                "Cúm mùa",
                "Vaxigrip Tetra",
                "01/10/2024",
                "Trung tâm Y tế Quận 1",
                "Lô: VG789012",
                "Đã hoàn thành"
        ));

        certificateAdapter.updateData(vaccinationRecords);
    }
}