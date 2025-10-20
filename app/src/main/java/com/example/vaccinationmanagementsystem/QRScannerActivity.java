package com.example.vaccinationmanagementsystem;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private static final int CAMERA_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        initViews();
        checkCameraPermission();
    }

    private void initViews() {
        scannerView = findViewById(R.id.scannerView);
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST);
        } else {
            startScanning();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning();
            } else {
                Toast.makeText(this, "Cần quyền truy cập camera để quét mã QR", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void startScanning() {
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String qrCode = rawResult.getText();
        
        // Xử lý mã QR
        processQRCode(qrCode);
        
        // Tiếp tục quét
        scannerView.resumeCameraPreview(this);
    }

    private void processQRCode(String qrCode) {
        // Giả lập xử lý mã QR
        Toast.makeText(this, "Mã QR: " + qrCode, Toast.LENGTH_LONG).show();
        
        // Có thể thêm logic xử lý mã QR cụ thể ở đây
        // Ví dụ: kiểm tra thông tin vaccine, xác thực chứng nhận, etc.
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (scannerView != null) {
            scannerView.resumeCameraPreview(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (scannerView != null) {
            scannerView.stopCamera();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scannerView != null) {
            scannerView.stopCamera();
        }
    }
}