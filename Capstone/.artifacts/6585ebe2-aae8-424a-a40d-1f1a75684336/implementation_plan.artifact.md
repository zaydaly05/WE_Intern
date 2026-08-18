# Implementation Plan - Fix AGP 9.0 Built-in Kotlin Conflict

The project is encountering the error `Cannot add extension with name 'kotlin', as there is an extension already registered with that name.` during Gradle sync. This is due to the use of Android Gradle Plugin (AGP) 9.3.1, which introduces built-in Kotlin support. In AGP 9.0+, the `org.jetbrains.kotlin.android` plugin is no longer required and conflicts with the built-in extension. Additionally, `org.jetbrains.kotlin.kapt` must be replaced with `com.android.legacy-kapt`.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/gradle/libs.versions.toml)
- Add `legacy-kapt = { id = "com.android.legacy-kapt", version.ref = "agp" }` to `[plugins]`.
- Remove `kotlin-android` and `kotlin-kapt` from `[plugins]`.

#### [MODIFY] [build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/build.gradle.kts) (Root)
- Remove `alias(libs.plugins.kotlin.android) apply false`.
- Replace `alias(libs.plugins.kotlin.kapt) apply false` with `alias(libs.plugins.legacy-kapt) apply false`.

#### [MODIFY] [app/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/build.gradle.kts)
- Remove `alias(libs.plugins.kotlin.android)`.
- Replace `alias(libs.plugins.kotlin.kapt)` with `alias(libs.plugins.legacy-kapt)`.

#### [MODIFY] [core-network/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/core-network/build.gradle.kts)
- Remove `alias(libs.plugins.kotlin.android)`.
- Replace `alias(libs.plugins.kotlin.kapt)` with `alias(libs.plugins.legacy-kapt)`.

#### [MODIFY] [feature-profile/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/feature-profile/build.gradle.kts)
- Remove `alias(libs.plugins.kotlin.android)`.
- Replace `alias(libs.plugins.kotlin.kapt)` with `alias(libs.plugins.legacy-kapt)`.

## Verification Plan

### Automated Tests
- Run `./gradlew help` or trigger a Gradle Sync in Android Studio to verify that the project configuration is successful.
- Run a build to ensure Kotlin compilation and Kapt (via legacy-kapt) work as expected.

### Manual Verification
- Confirm that the `kotlin` extension error no longer appears in the Build output.
