package com.example.vaccinationmanagementsystem;

public class Feature {
    private String name;
    private String description;
    private int iconResId;
    private String activityName; // Tên activity để chuyển đến
    private int colorResId; // Màu nền (tuỳ chọn)

    // Constructor đầy đủ
    public Feature(String name, String description, int iconResId, String activityName, int colorResId) {
        this.name = name;
        this.description = description;
        this.iconResId = iconResId;
        this.activityName = activityName;
        this.colorResId = colorResId;
    }

    // Constructor đơn giản
    public Feature(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
        this.description = "";
        this.activityName = "";
        this.colorResId = android.R.color.white;
    }

    // Constructor với activity
    public Feature(String name, int iconResId, String activityName) {
        this.name = name;
        this.iconResId = iconResId;
        this.activityName = activityName;
        this.description = "";
        this.colorResId = android.R.color.white;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getColorResId() {
        return colorResId;
    }

    public void setColorResId(int colorResId) {
        this.colorResId = colorResId;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", iconResId=" + iconResId +
                ", activityName='" + activityName + '\'' +
                '}';
    }
}