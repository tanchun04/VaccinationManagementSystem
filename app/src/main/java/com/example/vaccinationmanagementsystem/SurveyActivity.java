package com.example.vaccinationmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {

    private RadioGroup rgSideEffects, rgPainLevel, rgOverallExperience;
    private Button btnSubmitSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        rgSideEffects = findViewById(R.id.rgSideEffects);
        rgPainLevel = findViewById(R.id.rgPainLevel);
        rgOverallExperience = findViewById(R.id.rgOverallExperience);
        btnSubmitSurvey = findViewById(R.id.btnSubmitSurvey);
    }

    private void setupClickListeners() {
        btnSubmitSurvey.setOnClickListener(v -> submitSurvey());
    }

    private void submitSurvey() {
        // Kiểm tra xem tất cả câu hỏi đã được trả lời chưa
        if (rgSideEffects.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Vui lòng trả lời câu hỏi về tác dụng phụ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rgPainLevel.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Vui lòng đánh giá mức độ đau", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rgOverallExperience.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Vui lòng đánh giá trải nghiệm tổng thể", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thu thập dữ liệu khảo sát
        String sideEffects = getSelectedOption(rgSideEffects);
        String painLevel = getSelectedOption(rgPainLevel);
        String overallExperience = getSelectedOption(rgOverallExperience);

        // Xử lý gửi khảo sát
        processSurveyData(sideEffects, painLevel, overallExperience);
    }

    private String getSelectedOption(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.rbSideEffectsNone:
                return "Không có tác dụng phụ";
            case R.id.rbSideEffectsMild:
                return "Tác dụng phụ nhẹ";
            case R.id.rbSideEffectsModerate:
                return "Tác dụng phụ vừa phải";
            case R.id.rbSideEffectsSevere:
                return "Tác dụng phụ nghiêm trọng";
            case R.id.rbPainNone:
                return "Không đau";
            case R.id.rbPainMild:
                return "Đau nhẹ";
            case R.id.rbPainModerate:
                return "Đau vừa phải";
            case R.id.rbPainSevere:
                return "Đau nhiều";
            case R.id.rbExperienceExcellent:
                return "Rất tốt";
            case R.id.rbExperienceGood:
                return "Tốt";
            case R.id.rbExperienceFair:
                return "Bình thường";
            case R.id.rbExperiencePoor:
                return "Kém";
            default:
                return "";
        }
    }

    private void processSurveyData(String sideEffects, String painLevel, String overallExperience) {
        // Lưu dữ liệu khảo sát
        // Có thể lưu vào database hoặc gửi lên server
        
        Toast.makeText(this, "Cảm ơn bạn đã tham gia khảo sát!", Toast.LENGTH_SHORT).show();
        finish();
    }
}