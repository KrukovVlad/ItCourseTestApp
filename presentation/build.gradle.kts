plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.itcoursetestapp.presentation"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":domain"))

    // AndroidX & UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // AdapterDelegates
    implementation(libs.adapter.delegates)

    // Coroutines
    implementation(libs.coroutines.android)

    // Koin
    implementation(libs.koin.android)
}