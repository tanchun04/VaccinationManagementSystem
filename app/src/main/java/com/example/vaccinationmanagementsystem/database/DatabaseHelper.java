package com.example.vaccinationmanagementsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "vaccination_management.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String TABLE_VACCINATION_RECORDS = "vaccination_records";
    public static final String TABLE_VACCINES = "vaccines";

    // Users table columns
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_FULL_NAME = "full_name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_ADDRESS = "address";
    public static final String COLUMN_USER_CREATED_AT = "created_at";

    // Appointments table columns
    public static final String COLUMN_APPOINTMENT_ID = "appointment_id";
    public static final String COLUMN_APPOINTMENT_USER_ID = "user_id";
    public static final String COLUMN_APPOINTMENT_VACCINE_ID = "vaccine_id";
    public static final String COLUMN_APPOINTMENT_DATE = "appointment_date";
    public static final String COLUMN_APPOINTMENT_TIME = "appointment_time";
    public static final String COLUMN_APPOINTMENT_STATUS = "status";
    public static final String COLUMN_APPOINTMENT_LOCATION = "location";
    public static final String COLUMN_APPOINTMENT_CREATED_AT = "created_at";

    // Vaccination records table columns
    public static final String COLUMN_RECORD_ID = "record_id";
    public static final String COLUMN_RECORD_USER_ID = "user_id";
    public static final String COLUMN_RECORD_VACCINE_ID = "vaccine_id";
    public static final String COLUMN_RECORD_VACCINATION_DATE = "vaccination_date";
    public static final String COLUMN_RECORD_DOSE_NUMBER = "dose_number";
    public static final String COLUMN_RECORD_BATCH_NUMBER = "batch_number";
    public static final String COLUMN_RECORD_DOCTOR = "doctor";
    public static final String COLUMN_RECORD_LOCATION = "location";
    public static final String COLUMN_RECORD_CERTIFICATE_ID = "certificate_id";
    public static final String COLUMN_RECORD_CREATED_AT = "created_at";

    // Vaccines table columns
    public static final String COLUMN_VACCINE_ID = "vaccine_id";
    public static final String COLUMN_VACCINE_NAME = "vaccine_name";
    public static final String COLUMN_VACCINE_TYPE = "vaccine_type";
    public static final String COLUMN_VACCINE_MANUFACTURER = "manufacturer";
    public static final String COLUMN_VACCINE_DESCRIPTION = "description";
    public static final String COLUMN_VACCINE_DOSES_REQUIRED = "doses_required";
    public static final String COLUMN_VACCINE_INTERVAL_DAYS = "interval_days";

    // Create table statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_FULL_NAME + " TEXT NOT NULL,"
            + COLUMN_USER_EMAIL + " TEXT UNIQUE NOT NULL,"
            + COLUMN_USER_PHONE + " TEXT NOT NULL,"
            + COLUMN_USER_PASSWORD + " TEXT NOT NULL,"
            + COLUMN_USER_ADDRESS + " TEXT,"
            + COLUMN_USER_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    private static final String CREATE_TABLE_APPOINTMENTS = "CREATE TABLE " + TABLE_APPOINTMENTS + "("
            + COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_APPOINTMENT_USER_ID + " INTEGER NOT NULL,"
            + COLUMN_APPOINTMENT_VACCINE_ID + " INTEGER NOT NULL,"
            + COLUMN_APPOINTMENT_DATE + " TEXT NOT NULL,"
            + COLUMN_APPOINTMENT_TIME + " TEXT NOT NULL,"
            + COLUMN_APPOINTMENT_STATUS + " TEXT DEFAULT 'scheduled',"
            + COLUMN_APPOINTMENT_LOCATION + " TEXT,"
            + COLUMN_APPOINTMENT_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + "FOREIGN KEY(" + COLUMN_APPOINTMENT_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "),"
            + "FOREIGN KEY(" + COLUMN_APPOINTMENT_VACCINE_ID + ") REFERENCES " + TABLE_VACCINES + "(" + COLUMN_VACCINE_ID + ")"
            + ")";

    private static final String CREATE_TABLE_VACCINATION_RECORDS = "CREATE TABLE " + TABLE_VACCINATION_RECORDS + "("
            + COLUMN_RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_RECORD_USER_ID + " INTEGER NOT NULL,"
            + COLUMN_RECORD_VACCINE_ID + " INTEGER NOT NULL,"
            + COLUMN_RECORD_VACCINATION_DATE + " TEXT NOT NULL,"
            + COLUMN_RECORD_DOSE_NUMBER + " INTEGER NOT NULL,"
            + COLUMN_RECORD_BATCH_NUMBER + " TEXT,"
            + COLUMN_RECORD_DOCTOR + " TEXT,"
            + COLUMN_RECORD_LOCATION + " TEXT,"
            + COLUMN_RECORD_CERTIFICATE_ID + " TEXT,"
            + COLUMN_RECORD_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + "FOREIGN KEY(" + COLUMN_RECORD_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "),"
            + "FOREIGN KEY(" + COLUMN_RECORD_VACCINE_ID + ") REFERENCES " + TABLE_VACCINES + "(" + COLUMN_VACCINE_ID + ")"
            + ")";

    private static final String CREATE_TABLE_VACCINES = "CREATE TABLE " + TABLE_VACCINES + "("
            + COLUMN_VACCINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_VACCINE_NAME + " TEXT NOT NULL,"
            + COLUMN_VACCINE_TYPE + " TEXT NOT NULL,"
            + COLUMN_VACCINE_MANUFACTURER + " TEXT,"
            + COLUMN_VACCINE_DESCRIPTION + " TEXT,"
            + COLUMN_VACCINE_DOSES_REQUIRED + " INTEGER DEFAULT 1,"
            + COLUMN_VACCINE_INTERVAL_DAYS + " INTEGER DEFAULT 0"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_VACCINES);
        db.execSQL(CREATE_TABLE_APPOINTMENTS);
        db.execSQL(CREATE_TABLE_VACCINATION_RECORDS);
        
        // Insert default vaccines
        insertDefaultVaccines(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACCINATION_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACCINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    private void insertDefaultVaccines(SQLiteDatabase db) {
        String[] insertStatements = {
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('COVID-19 (Pfizer)', 'mRNA', 'Pfizer-BioNTech', 'Vaccine phòng COVID-19', 2, 21)",
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('COVID-19 (AstraZeneca)', 'Viral Vector', 'AstraZeneca', 'Vaccine phòng COVID-19', 2, 28)",
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('COVID-19 (Moderna)', 'mRNA', 'Moderna', 'Vaccine phòng COVID-19', 2, 28)",
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('Cúm mùa', 'Inactivated', 'Sanofi Pasteur', 'Vaccine phòng cúm mùa', 1, 0)",
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('Viêm gan B', 'Recombinant', 'GSK', 'Vaccine phòng viêm gan B', 3, 30)",
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('Sởi - Rubella', 'Live Attenuated', 'Merck', 'Vaccine phòng sởi và rubella', 2, 28)",
            "INSERT INTO " + TABLE_VACCINES + " (" + COLUMN_VACCINE_NAME + ", " + COLUMN_VACCINE_TYPE + ", " + COLUMN_VACCINE_MANUFACTURER + ", " + COLUMN_VACCINE_DESCRIPTION + ", " + COLUMN_VACCINE_DOSES_REQUIRED + ", " + COLUMN_VACCINE_INTERVAL_DAYS + ") VALUES ('Bạch hầu - Ho gà - Uốn ván', 'Toxoid', 'GSK', 'Vaccine phòng bạch hầu, ho gà và uốn ván', 3, 60)"
        };

        for (String statement : insertStatements) {
            db.execSQL(statement);
        }
    }
}