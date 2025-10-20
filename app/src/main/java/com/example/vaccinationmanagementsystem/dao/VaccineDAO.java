package com.example.vaccinationmanagementsystem.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.vaccinationmanagementsystem.database.DatabaseHelper;
import com.example.vaccinationmanagementsystem.model.Vaccine;
import java.util.ArrayList;
import java.util.List;

public class VaccineDAO {
    private DatabaseHelper dbHelper;

    public VaccineDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Lấy tất cả vaccines
    public List<Vaccine> getAllVaccines() {
        List<Vaccine> vaccineList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {
            DatabaseHelper.COLUMN_VACCINE_ID,
            DatabaseHelper.COLUMN_VACCINE_NAME,
            DatabaseHelper.COLUMN_VACCINE_TYPE,
            DatabaseHelper.COLUMN_VACCINE_MANUFACTURER,
            DatabaseHelper.COLUMN_VACCINE_DESCRIPTION,
            DatabaseHelper.COLUMN_VACCINE_DOSES_REQUIRED,
            DatabaseHelper.COLUMN_VACCINE_INTERVAL_DAYS
        };
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_VACCINES, columns, null, null, 
                               null, null, DatabaseHelper.COLUMN_VACCINE_NAME + " ASC");
        
        if (cursor.moveToFirst()) {
            do {
                Vaccine vaccine = new Vaccine();
                vaccine.setVaccineId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_ID)));
                vaccine.setVaccineName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_NAME)));
                vaccine.setVaccineType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_TYPE)));
                vaccine.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_MANUFACTURER)));
                vaccine.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_DESCRIPTION)));
                vaccine.setDosesRequired(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_DOSES_REQUIRED)));
                vaccine.setIntervalDays(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_INTERVAL_DAYS)));
                vaccineList.add(vaccine);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return vaccineList;
    }

    // Lấy vaccine theo ID
    public Vaccine getVaccineById(int vaccineId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {
            DatabaseHelper.COLUMN_VACCINE_ID,
            DatabaseHelper.COLUMN_VACCINE_NAME,
            DatabaseHelper.COLUMN_VACCINE_TYPE,
            DatabaseHelper.COLUMN_VACCINE_MANUFACTURER,
            DatabaseHelper.COLUMN_VACCINE_DESCRIPTION,
            DatabaseHelper.COLUMN_VACCINE_DOSES_REQUIRED,
            DatabaseHelper.COLUMN_VACCINE_INTERVAL_DAYS
        };
        
        String selection = DatabaseHelper.COLUMN_VACCINE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(vaccineId)};
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_VACCINES, columns, selection, 
                               selectionArgs, null, null, null);
        
        Vaccine vaccine = null;
        if (cursor.moveToFirst()) {
            vaccine = new Vaccine();
            vaccine.setVaccineId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_ID)));
            vaccine.setVaccineName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_NAME)));
            vaccine.setVaccineType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_TYPE)));
            vaccine.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_MANUFACTURER)));
            vaccine.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_DESCRIPTION)));
            vaccine.setDosesRequired(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_DOSES_REQUIRED)));
            vaccine.setIntervalDays(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_INTERVAL_DAYS)));
        }
        
        cursor.close();
        db.close();
        return vaccine;
    }

    // Lấy vaccine theo tên
    public Vaccine getVaccineByName(String vaccineName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {
            DatabaseHelper.COLUMN_VACCINE_ID,
            DatabaseHelper.COLUMN_VACCINE_NAME,
            DatabaseHelper.COLUMN_VACCINE_TYPE,
            DatabaseHelper.COLUMN_VACCINE_MANUFACTURER,
            DatabaseHelper.COLUMN_VACCINE_DESCRIPTION,
            DatabaseHelper.COLUMN_VACCINE_DOSES_REQUIRED,
            DatabaseHelper.COLUMN_VACCINE_INTERVAL_DAYS
        };
        
        String selection = DatabaseHelper.COLUMN_VACCINE_NAME + " = ?";
        String[] selectionArgs = {vaccineName};
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_VACCINES, columns, selection, 
                               selectionArgs, null, null, null);
        
        Vaccine vaccine = null;
        if (cursor.moveToFirst()) {
            vaccine = new Vaccine();
            vaccine.setVaccineId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_ID)));
            vaccine.setVaccineName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_NAME)));
            vaccine.setVaccineType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_TYPE)));
            vaccine.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_MANUFACTURER)));
            vaccine.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_DESCRIPTION)));
            vaccine.setDosesRequired(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_DOSES_REQUIRED)));
            vaccine.setIntervalDays(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_INTERVAL_DAYS)));
        }
        
        cursor.close();
        db.close();
        return vaccine;
    }

    // Lấy danh sách tên vaccine cho spinner
    public List<String> getVaccineNames() {
        List<String> vaccineNames = new ArrayList<>();
        vaccineNames.add("Chọn loại vaccine"); // Default option
        
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        String[] columns = {DatabaseHelper.COLUMN_VACCINE_NAME};
        
        Cursor cursor = db.query(DatabaseHelper.TABLE_VACCINES, columns, null, null, 
                               null, null, DatabaseHelper.COLUMN_VACCINE_NAME + " ASC");
        
        if (cursor.moveToFirst()) {
            do {
                vaccineNames.add(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VACCINE_NAME)));
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return vaccineNames;
    }
}