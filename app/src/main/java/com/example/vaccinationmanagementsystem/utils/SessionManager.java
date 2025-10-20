package com.example.vaccinationmanagementsystem.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.vaccinationmanagementsystem.model.User;

public class SessionManager {
    private static final String PREF_NAME = "VaccinationSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_PHONE = "userPhone";
    private static final String KEY_USER_ADDRESS = "userAddress";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // Tạo session đăng nhập
    public void createLoginSession(User user) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_USER_NAME, user.getFullName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_PHONE, user.getPhone());
        editor.putString(KEY_USER_ADDRESS, user.getAddress());
        editor.commit();
    }

    // Kiểm tra đã đăng nhập chưa
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Lấy thông tin user hiện tại
    public User getCurrentUser() {
        if (!isLoggedIn()) {
            return null;
        }

        User user = new User();
        user.setUserId(pref.getInt(KEY_USER_ID, -1));
        user.setFullName(pref.getString(KEY_USER_NAME, ""));
        user.setEmail(pref.getString(KEY_USER_EMAIL, ""));
        user.setPhone(pref.getString(KEY_USER_PHONE, ""));
        user.setAddress(pref.getString(KEY_USER_ADDRESS, ""));
        return user;
    }

    // Lấy user ID
    public int getCurrentUserId() {
        return pref.getInt(KEY_USER_ID, -1);
    }

    // Lấy tên user
    public String getCurrentUserName() {
        return pref.getString(KEY_USER_NAME, "");
    }

    // Lấy email user
    public String getCurrentUserEmail() {
        return pref.getString(KEY_USER_EMAIL, "");
    }

    // Cập nhật thông tin user
    public void updateUserInfo(User user) {
        editor.putString(KEY_USER_NAME, user.getFullName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_PHONE, user.getPhone());
        editor.putString(KEY_USER_ADDRESS, user.getAddress());
        editor.commit();
    }

    // Đăng xuất
    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}