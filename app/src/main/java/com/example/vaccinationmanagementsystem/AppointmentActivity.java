package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAppointments;
    private AppointmentAdapter appointmentAdapter;
    private List<Appointment> appointmentList;
    private Button btnNewAppointment, btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        initViews();
        setupRecyclerView();
        loadAppointmentData();
        setupClickListeners();
    }

    private void initViews() {
        recyclerViewAppointments = findViewById(R.id.recyclerViewAppointments);
        btnNewAppointment = findViewById(R.id.btnNewAppointment);
        btnRefresh = findViewById(R.id.btnRefresh);
    }

    private void setupRecyclerView() {
        recyclerViewAppointments.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(appointmentList, this::onAppointmentAction);
        recyclerViewAppointments.setAdapter(appointmentAdapter);
    }

    private void loadAppointmentData() {
        appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(
                "1",
                "COVID-19 Vaccine - Mũi 1",
                "15/03/2024",
                "08:00",
                "Bệnh viện Chợ Rẫy",
                "Đã hoàn thành"
        ));
        appointmentList.add(new Appointment(
                "2",
                "COVID-19 Vaccine - Mũi 2",
                "15/04/2024",
                "08:00",
                "Bệnh viện Chợ Rẫy",
                "Đã hoàn thành"
        ));
        appointmentList.add(new Appointment(
                "3",
                "Cúm mùa",
                "15/10/2024",
                "09:30",
                "Trung tâm Y tế Quận 1",
                "Đang chờ"
        ));
        appointmentList.add(new Appointment(
                "4",
                "Hepatitis B",
                "20/11/2024",
                "14:00",
                "Bệnh viện Nhi Đồng 1",
                "Đã hủy"
        ));

        appointmentAdapter.updateData(appointmentList);
    }

    private void setupClickListeners() {
        btnNewAppointment.setOnClickListener(v -> createNewAppointment());
        btnRefresh.setOnClickListener(v -> refreshAppointments());
    }

    private void createNewAppointment() {
        // Chuyển đến màn hình đặt lịch mới
        // Intent intent = new Intent(this, ScheduleActivity.class);
        // startActivity(intent);
        Toast.makeText(this, "Chuyển đến đặt lịch mới", Toast.LENGTH_SHORT).show();
    }

    private void refreshAppointments() {
        loadAppointmentData();
        Toast.makeText(this, "Đã làm mới danh sách lịch hẹn", Toast.LENGTH_SHORT).show();
    }

    private void onAppointmentAction(String action, Appointment appointment) {
        switch (action) {
            case "cancel":
                cancelAppointment(appointment);
                break;
            case "reschedule":
                rescheduleAppointment(appointment);
                break;
            case "view":
                viewAppointmentDetails(appointment);
                break;
        }
    }

    private void cancelAppointment(Appointment appointment) {
        // Logic hủy lịch hẹn
        Toast.makeText(this, "Đã hủy lịch hẹn: " + appointment.getVaccineType(), Toast.LENGTH_SHORT).show();
    }

    private void rescheduleAppointment(Appointment appointment) {
        // Logic đặt lại lịch hẹn
        Toast.makeText(this, "Đặt lại lịch hẹn: " + appointment.getVaccineType(), Toast.LENGTH_SHORT).show();
    }

    private void viewAppointmentDetails(Appointment appointment) {
        // Logic xem chi tiết lịch hẹn
        Toast.makeText(this, "Chi tiết: " + appointment.getVaccineType(), Toast.LENGTH_SHORT).show();
    }
}