package com.example.vaccinationmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FeatureAdapter.OnFeatureClickListener {

    private GridView gridViewFeatures;
    private FeatureAdapter featureAdapter;
    private ArrayList<Feature> featureList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupFeatureList();
        setupGridView();
    }

    private void initViews() {
        gridViewFeatures = findViewById(R.id.gridViewFeatures);
        sharedPreferences = getSharedPreferences("VaccinationPrefs", MODE_PRIVATE);
    }

    private void setupFeatureList() {
        featureList = new ArrayList<>();

        // Thêm các tính năng dựa trên use case diagram
        featureList.add(new Feature("Đặt lịch tiêm",
                "Đặt lịch hẹn tiêm chủng",
                R.drawable.ic_schedule,
                "ScheduleActivity",
                R.color.light_blue));

        featureList.add(new Feature("Xem chứng nhận",
                "Xem chứng nhận tiêm chủng",
                R.drawable.ic_certificate,
                "CertificateActivity",
                R.color.light_green));

        featureList.add(new Feature("Quét mã QR",
                "Quét mã QR định danh",
                R.drawable.ic_qr_code,
                "QRScannerActivity",
                R.color.light_orange));

        featureList.add(new Feature("Khảo sát sau tiêm",
                "Khảo sát phản ứng sau tiêm",
                R.drawable.ic_survey,
                "SurveyActivity",
                R.color.light_purple));

        featureList.add(new Feature("Thống kê",
                "Xem thống kê tiêm chủng",
                R.drawable.ic_statistics,
                "StatisticsActivity",
                R.color.light_red));

        featureList.add(new Feature("Quản lý vaccine",
                "Theo dõi vaccine tồn kho",
                R.drawable.ic_vaccine,
                "VaccineManagementActivity",
                R.color.light_teal));

        featureList.add(new Feature("Nhận thông báo",
                "Nhận thông báo nhắc lịch",
                R.drawable.ic_notification,
                "NotificationActivity",
                R.color.light_yellow));

        featureList.add(new Feature("Hủy/Đặt lịch",
                "Hủy hoặc đặt lại lịch hẹn",
                R.drawable.ic_calendar,
                "AppointmentActivity",
                R.color.light_indigo));

        featureList.add(new Feature("Cập nhật trạng thái",
                "Cập nhật trạng thái tiêm",
                R.drawable.ic_update,
                "UpdateStatusActivity",
                R.color.light_cyan));

        featureList.add(new Feature("Quản lý tài khoản",
                "Quản lý thông tin cá nhân",
                R.drawable.ic_account,
                "AccountActivity",
                R.color.light_pink));
    }

    private void setupGridView() {
        // Khởi tạo adapter với listener
        featureAdapter = new FeatureAdapter(this, featureList, this);
        gridViewFeatures.setAdapter(featureAdapter);
    }

    @Override
    public void onFeatureClick(Feature feature) {
        // Xử lý khi người dùng click vào feature
        handleFeatureNavigation(feature);
    }

    private void handleFeatureNavigation(Feature feature) {
        String activitySimpleName = feature.getActivityName();
        if (activitySimpleName == null || activitySimpleName.trim().isEmpty()) {
            launchStub(feature);
            return;
        }

        // Try to launch by fully qualified class name within this package
        String packageName = getPackageName();
        String fqcn = packageName + "." + activitySimpleName;
        try {
            Class<?> clazz = Class.forName(fqcn);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            // Fallback to stub activity
            launchStub(feature);
        }
    }

    private void launchStub(Feature feature) {
        Intent intent = new Intent(this, FeatureStubActivity.class);
        intent.putExtra("title", feature.getName());
        intent.putExtra("description", feature.getDescription());
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}