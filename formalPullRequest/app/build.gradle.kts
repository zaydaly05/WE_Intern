plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.example.formalpullrequest"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.formalpullrequest"
        minSdk = 24
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // ========================================================
    // Compose
    // ========================================================

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation(libs.androidx.compose.ui.tooling)


    // ========================================================
    // Android Core
    // ========================================================

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)


    // ========================================================
    // Lifecycle / ViewModel
    // ========================================================

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)


    // ========================================================
    // Navigation
    // ========================================================

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.hilt.navigation.compose)


    // ========================================================
    // Kotlin Coroutines
    // ========================================================

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)


    // ========================================================
    // Kotlin Serialization
    // ========================================================

    implementation(libs.kotlinx.serialization.json)


    // ========================================================
    // Retrofit
    // ========================================================

    implementation(libs.retrofit)

    implementation(libs.retrofit.converter.kotlinx.serialization)


    // ========================================================
    // OkHttp
    // ========================================================

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)


    // ========================================================
    // Room
    // ========================================================

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    ksp(libs.androidx.room.compiler)


    // ========================================================
    // Hilt
    // ========================================================

    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)


    // ========================================================
    // Unit Testing
    // ========================================================

    testImplementation(libs.junit)


    // ========================================================
    // Android Testing
    // ========================================================

    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(platform(libs.firebase.bom))

    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    // ========================================================
    // Debug
    // ========================================================

    debugImplementation(libs.androidx.compose.ui.test.manifest)
}