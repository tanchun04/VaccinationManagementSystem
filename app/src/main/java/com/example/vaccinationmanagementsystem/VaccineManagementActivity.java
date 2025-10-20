package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class VaccineManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerViewVaccines;
    private VaccineAdapter vaccineAdapter;
    private List<Vaccine> vaccineList;
    private EditText etVaccineName, etVaccineLot, etQuantity, etExpiryDate;
    private Button btnAddVaccine, btnUpdateVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_management);

        initViews();
        setupRecyclerView();
        loadVaccineData();
        setupClickListeners();
    }

    private void initViews() {
        recyclerViewVaccines = findViewById(R.id.recyclerViewVaccines);
        etVaccineName = findViewById(R.id.etVaccineName);
        etVaccineLot = findViewById(R.id.etVaccineLot);
        etQuantity = findViewById(R.id.etQuantity);
        etExpiryDate = findViewById(R.id.etExpiryDate);
        btnAddVaccine = findViewById(R.id.btnAddVaccine);
        btnUpdateVaccine = findViewById(R.id.btnUpdateVaccine);
    }

    private void setupRecyclerView() {
        recyclerViewVaccines.setLayoutManager(new LinearLayoutManager(this));
        vaccineAdapter = new VaccineAdapter(vaccineList);
        recyclerViewVaccines.setAdapter(vaccineAdapter);
    }

    private void loadVaccineData() {
        vaccineList = new ArrayList<>();
        vaccineList.add(new Vaccine("COVID-19 Vaccine", "Pfizer-BioNTech", "PF123456", 150, "31/12/2024"));
        vaccineList.add(new Vaccine("COVID-19 Vaccine", "Moderna", "MD789012", 200, "30/11/2024"));
        vaccineList.add(new Vaccine("Cúm mùa", "Vaxigrip Tetra", "VG345678", 300, "28/02/2025"));
        vaccineList.add(new Vaccine("Hepatitis B", "Engerix-B", "HB901234", 100, "15/06/2025"));

        vaccineAdapter.updateData(vaccineList);
    }

    private void setupClickListeners() {
        btnAddVaccine.setOnClickListener(v -> addVaccine());
        btnUpdateVaccine.setOnClickListener(v -> updateVaccine());
    }

    private void addVaccine() {
        String name = etVaccineName.getText().toString().trim();
        String lot = etVaccineLot.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();
        String expiryDate = etExpiryDate.getText().toString().trim();

        if (name.isEmpty() || lot.isEmpty() || quantityStr.isEmpty() || expiryDate.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            Vaccine newVaccine = new Vaccine(name, "", lot, quantity, expiryDate);
            vaccineList.add(newVaccine);
            vaccineAdapter.updateData(vaccineList);
            clearFields();
            Toast.makeText(this, "Thêm vaccine thành công", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số lượng phải là số nguyên", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateVaccine() {
        // Logic cập nhật vaccine
        Toast.makeText(this, "Chức năng cập nhật đang phát triển", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        etVaccineName.setText("");
        etVaccineLot.setText("");
        etQuantity.setText("");
        etExpiryDate.setText("");
    }
}