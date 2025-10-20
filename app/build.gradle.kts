plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.vaccinationmanagementsystem"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vaccinationmanagementsystem"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // ZXing - prefer JourneyApps, avoid deprecated dm7 to prevent conflicts
    implementation(libs.zxing.embedded)
    implementation(libs.zxing.core)
    implementation(libs.cardview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}