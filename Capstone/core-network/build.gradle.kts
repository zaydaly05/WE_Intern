plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.legacyKapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.capstone.core.network"
    compileSdk = 37
    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
