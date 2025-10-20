package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class UpdateStatusActivity extends AppCompatActivity {

    private Spinner spinnerVaccine, spinnerStatus;
    private EditText etNotes, etLocation, etDate;
    private Button btnUpdateStatus, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        initViews();
        setupSpinners();
        setupClickListeners();
    }

    private void initViews() {
        spinnerVaccine = findViewById(R.id.spinnerVaccine);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        etNotes = findViewById(R.id.etNotes);
        etLocation = findViewById(R.id.etLocation);
        etDate = findViewById(R.id.etDate);
        btnUpdateStatus = findViewById(R.id.btnUpdateStatus);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setupSpinners() {
        // Thiết lập dữ liệu cho spinner vaccine
        List<String> vaccineList = new ArrayList<>();
        vaccineList.add("COVID-19 Vaccine - Mũi 1");
        vaccineList.add("COVID-19 Vaccine - Mũi 2");
        vaccineList.add("Cúm mùa");
        vaccineList.add("Hepatitis B");
        vaccineList.add("MMR");

        // Thiết lập dữ liệu cho spinner status
        List<String> statusList = new ArrayList<>();
        statusList.add("Đã tiêm");
        statusList.add("Chưa tiêm");
        statusList.add("Hoãn tiêm");
        statusList.add("Từ chối tiêm");
        statusList.add("Cần theo dõi");
    }

    private void setupClickListeners() {
        btnUpdateStatus.setOnClickListener(v -> updateVaccinationStatus());
        btnSave.setOnClickListener(v -> saveStatusUpdate());
    }

    private void updateVaccinationStatus() {
        String selectedVaccine = spinnerVaccine.getSelectedItem().toString();
        String selectedStatus = spinnerStatus.getSelectedItem().toString();
        String notes = etNotes.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        if (selectedVaccine.isEmpty() || selectedStatus.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn vaccine và trạng thái", Toast.LENGTH_SHORT).show();
            return;
        }

        // Xử lý cập nhật trạng thái
        processStatusUpdate(selectedVaccine, selectedStatus, notes, location, date);
    }

    private void processStatusUpdate(String vaccine, String status, String notes, String location, String date) {
        // Lưu thông tin cập nhật trạng thái
        // Có thể lưu vào database hoặc gửi lên server
        
        Toast.makeText(this, 
                String.format("Đã cập nhật trạng thái %s cho %s", status, vaccine), 
                Toast.LENGTH_SHORT).show();
        
        // Reset form
        etNotes.setText("");
        etLocation.setText("");
        etDate.setText("");
    }

    private void saveStatusUpdate() {
        Toast.makeText(this, "Đã lưu cập nhật trạng thái", Toast.LENGTH_SHORT).show();
        finish();
    }
}