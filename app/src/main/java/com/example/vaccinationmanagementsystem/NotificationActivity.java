package com.example.vaccinationmanagementsystem;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationActivity extends AppCompatActivity {

    private Switch switchVaccinationReminder, switchAppointmentReminder, switchSurveyReminder;
    private Button btnTestNotification, btnSaveSettings;
    private static final String CHANNEL_ID = "vaccination_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initViews();
        createNotificationChannel();
        setupClickListeners();
    }

    private void initViews() {
        switchVaccinationReminder = findViewById(R.id.switchVaccinationReminder);
        switchAppointmentReminder = findViewById(R.id.switchAppointmentReminder);
        switchSurveyReminder = findViewById(R.id.switchSurveyReminder);
        btnTestNotification = findViewById(R.id.btnTestNotification);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Vaccination Notifications";
            String description = "Notifications for vaccination reminders and updates";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setupClickListeners() {
        btnTestNotification.setOnClickListener(v -> sendTestNotification());
        btnSaveSettings.setOnClickListener(v -> saveNotificationSettings());
    }

    private void sendTestNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Thông báo tiêm chủng")
                .setContentText("Đây là thông báo thử nghiệm từ hệ thống tiêm chủng")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

        Toast.makeText(this, "Đã gửi thông báo thử nghiệm", Toast.LENGTH_SHORT).show();
    }

    private void saveNotificationSettings() {
        boolean vaccinationReminder = switchVaccinationReminder.isChecked();
        boolean appointmentReminder = switchAppointmentReminder.isChecked();
        boolean surveyReminder = switchSurveyReminder.isChecked();

        // Lưu cài đặt thông báo
        // Có thể lưu vào SharedPreferences hoặc database
        
        Toast.makeText(this, "Đã lưu cài đặt thông báo", Toast.LENGTH_SHORT).show();
    }
}