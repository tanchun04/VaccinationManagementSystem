package com.example.vaccinationmanagementsystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.vaccinationmanagementsystem.database.DatabaseHelper;
import com.example.vaccinationmanagementsystem.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DatabaseHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Tạo user mới
    public long createUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(DatabaseHelper.COLUMN_USER_FULL_NAME, user.getFullName());
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_USER_PHONE, user.getPhone());
        values.put(DatabaseHelper.COLUMN_USER_PASSWORD, hashPassword(user.getPassword()));
        values.put(DatabaseHelper.COLUMN_USER_ADDRESS, user.getAddress());
        
        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        return result;
    }

    // Đăng nhập
    public User loginUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String hashedPassword = hashPassword(password);
        
        String[] columns = {
            DatabaseHelper.COLUMN_USER_ID,
            DatabaseHelper.COLUMN_USER_FULL_NAME,
            DatabaseHelper.COLUMN_USER_EMAIL,
            DatabaseHelper.COLUMN_USER_PHONE,
            DatabaseHelper.COLUMN_USER_ADDRESS,
            DatabaseHelper.COLUMN_USER_CREATED_AT
        };
        
        String selection = DatabaseHelper.COLUMN_USER_EMAIL + " = ? AND " + 
                          DatabaseHelper.COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, hashedPassword};
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, 
                               selectionArgs, null, null, null);
        
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_FULL_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_PHONE)));
            user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ADDRESS)));
            user.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_CREATED_AT)));
        }
        
        cursor.close();
        db.close();
        return user;
    }

    // Lấy user theo ID
    public User getUserById(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {
            DatabaseHelper.COLUMN_USER_ID,
            DatabaseHelper.COLUMN_USER_FULL_NAME,
            DatabaseHelper.COLUMN_USER_EMAIL,
            DatabaseHelper.COLUMN_USER_PHONE,
            DatabaseHelper.COLUMN_USER_ADDRESS,
            DatabaseHelper.COLUMN_USER_CREATED_AT
        };
        
        String selection = DatabaseHelper.COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, 
                               selectionArgs, null, null, null);
        
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_FULL_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_PHONE)));
            user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ADDRESS)));
            user.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_CREATED_AT)));
        }
        
        cursor.close();
        db.close();
        return user;
    }

    // Kiểm tra email đã tồn tại
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {DatabaseHelper.COLUMN_USER_ID};
        String selection = DatabaseHelper.COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, 
                               selectionArgs, null, null, null);
        
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Cập nhật thông tin user
    public int updateUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(DatabaseHelper.COLUMN_USER_FULL_NAME, user.getFullName());
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_USER_PHONE, user.getPhone());
        values.put(DatabaseHelper.COLUMN_USER_ADDRESS, user.getAddress());
        
        String whereClause = DatabaseHelper.COLUMN_USER_ID + " = ?";
        String[] whereArgs = {String.valueOf(user.getUserId())};
        
        int result = db.update(DatabaseHelper.TABLE_USERS, values, whereClause, whereArgs);
        db.close();
        return result;
    }

    // Lấy tất cả users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {
            DatabaseHelper.COLUMN_USER_ID,
            DatabaseHelper.COLUMN_USER_FULL_NAME,
            DatabaseHelper.COLUMN_USER_EMAIL,
            DatabaseHelper.COLUMN_USER_PHONE,
            DatabaseHelper.COLUMN_USER_ADDRESS,
            DatabaseHelper.COLUMN_USER_CREATED_AT
        };
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, null, null, 
                               null, null, DatabaseHelper.COLUMN_USER_CREATED_AT + " DESC");
        
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID)));
                user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_FULL_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_EMAIL)));
                user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_PHONE)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_CREATED_AT)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return userList;
    }

    // Hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password; // Fallback to plain text (not recommended for production)
        }
    }
}