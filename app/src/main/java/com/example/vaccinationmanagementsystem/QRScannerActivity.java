package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class QRScannerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_placeholder);
        setTitle("Quét mã QR");
    }
}
