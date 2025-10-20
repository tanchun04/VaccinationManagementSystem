package com.example.vaccinationmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    private EditText etFullName, etPhone, etEmail, etDate, etTime;
    private Spinner spinnerVaccineType, spinnerLocation;
    private Button btnSchedule, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etFullName = findViewById(R.id.etFullName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        spinnerVaccineType = findViewById(R.id.spinnerVaccineType);
        spinnerLocation = findViewById(R.id.spinnerLocation);
        btnSchedule = findViewById(R.id.btnSchedule);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void setupClickListeners() {
        etDate.setOnClickListener(v -> showDatePicker());
        etTime.setOnClickListener(v -> showTimePicker());
        
        btnSchedule.setOnClickListener(v -> scheduleVaccination());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    etDate.setText(date);
                }, year, month, day);
        
        // Chỉ cho phép chọn ngày từ hôm nay trở đi
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {
                    String time = String.format("%02d:%02d", hourOfDay, minute1);
                    etTime.setText(time);
                }, hour, minute, true);
        timePickerDialog.show();
    }

    private void scheduleVaccination() {
        String fullName = etFullName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();

        if (fullName.isEmpty()) {
            etFullName.setError("Vui lòng nhập họ tên");
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Vui lòng nhập số điện thoại");
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Vui lòng nhập email");
            return;
        }

        if (date.isEmpty()) {
            etDate.setError("Vui lòng chọn ngày tiêm");
            return;
        }

        if (time.isEmpty()) {
            etTime.setError("Vui lòng chọn giờ tiêm");
            return;
        }

        // Xử lý đặt lịch thành công
        Toast.makeText(this, "Đặt lịch tiêm thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }
}