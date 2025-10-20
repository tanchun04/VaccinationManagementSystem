package com.example.vaccinationmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.vaccinationmanagementsystem.dao.VaccineDAO;
import com.example.vaccinationmanagementsystem.utils.SessionManager;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private EditText etPatientName, etPatientPhone, etPatientAddress;
    private Spinner spinnerVaccineType, spinnerTimeSlot;
    private TextView tvSelectedDate;
    private Button btnSelectDate, btnSchedule;
    private Calendar selectedDate;
    private VaccineDAO vaccineDAO;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initViews();
        initDatabase();
        setupToolbar();
        setupSpinners();
        setupClickListeners();
    }

    private void initViews() {
        etPatientName = findViewById(R.id.etPatientName);
        etPatientPhone = findViewById(R.id.etPatientPhone);
        etPatientAddress = findViewById(R.id.etPatientAddress);
        spinnerVaccineType = findViewById(R.id.spinnerVaccineType);
        spinnerTimeSlot = findViewById(R.id.spinnerTimeSlot);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSchedule = findViewById(R.id.btnSchedule);
        selectedDate = Calendar.getInstance();
    }

    private void initDatabase() {
        vaccineDAO = new VaccineDAO(this);
        sessionManager = new SessionManager(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đặt lịch tiêm");
    }

    private void setupSpinners() {
        // Vaccine types from database
        List<String> vaccineTypes = vaccineDAO.getVaccineNames();
        ArrayAdapter<String> vaccineAdapter = new ArrayAdapter<>(this, 
            android.R.layout.simple_spinner_item, vaccineTypes);
        vaccineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVaccineType.setAdapter(vaccineAdapter);

        // Time slots
        String[] timeSlots = {
            "Chọn khung giờ",
            "08:00 - 09:00",
            "09:00 - 10:00",
            "10:00 - 11:00",
            "14:00 - 15:00",
            "15:00 - 16:00",
            "16:00 - 17:00"
        };
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_item, timeSlots);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeSlot.setAdapter(timeAdapter);
    }

    private void setupClickListeners() {
        btnSelectDate.setOnClickListener(v -> showDatePicker());
        btnSchedule.setOnClickListener(v -> scheduleAppointment());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (view, year, month, dayOfMonth) -> {
                selectedDate.set(year, month, dayOfMonth);
                String dateString = dayOfMonth + "/" + (month + 1) + "/" + year;
                tvSelectedDate.setText(dateString);
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        
        // Không cho chọn ngày trong quá khứ
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void scheduleAppointment() {
        String patientName = etPatientName.getText().toString().trim();
        String patientPhone = etPatientPhone.getText().toString().trim();
        String patientAddress = etPatientAddress.getText().toString().trim();
        String vaccineType = spinnerVaccineType.getSelectedItem().toString();
        String timeSlot = spinnerTimeSlot.getSelectedItem().toString();
        String selectedDateStr = tvSelectedDate.getText().toString();

        // Validation
        if (patientName.isEmpty()) {
            etPatientName.setError("Vui lòng nhập họ tên");
            return;
        }

        if (patientPhone.isEmpty()) {
            etPatientPhone.setError("Vui lòng nhập số điện thoại");
            return;
        }

        if (patientAddress.isEmpty()) {
            etPatientAddress.setError("Vui lòng nhập địa chỉ");
            return;
        }

        if (vaccineType.equals("Chọn loại vaccine")) {
            Toast.makeText(this, "Vui lòng chọn loại vaccine", Toast.LENGTH_SHORT).show();
            return;
        }

        if (timeSlot.equals("Chọn khung giờ")) {
            Toast.makeText(this, "Vui lòng chọn khung giờ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedDateStr.equals("Chưa chọn ngày")) {
            Toast.makeText(this, "Vui lòng chọn ngày tiêm", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Lưu vào database
        Toast.makeText(this, "Đặt lịch thành công!\nTên: " + patientName + 
            "\nVaccine: " + vaccineType + "\nNgày: " + selectedDateStr + 
            "\nGiờ: " + timeSlot, Toast.LENGTH_LONG).show();
        
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}