plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.itcoursetestapp.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":domain"))
    
    // Core
    implementation(libs.androidx.core.ktx)
    
    // Coroutines
    implementation(libs.coroutines.core)
    
    // Koin
    implementation(libs.koin.core)
    
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)
}