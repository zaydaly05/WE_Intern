# Walkthrough - Fix Kapt NonExistentClass Error

I have resolved the `NonExistentClass` error encountered during Kapt stub generation in the `:feature-profile` module.

## Changes Made

### Core Network Module
- **Transitive Dependencies**: Changed Retrofit, Gson Converter, and OkHttp dependencies from `implementation` to `api` in [core-network/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/core-network/build.gradle.kts).
  - This ensures that modules depending on `:core-network` (like `:feature-profile`) have access to Retrofit's annotations (e.g., `@GET`) during compilation and Kapt processing.

### Dependency Configuration
- **Hilt Compiler**: Updated `hilt-compiler` in [libs.versions.toml](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/gradle/libs.versions.toml) to use `com.google.dagger:hilt-android-compiler`.
  - While not the direct cause of the error, using the Android-specific compiler is best practice for Hilt in Android projects.

## Verification Results

### Automated Tests
- Ran `./gradlew :feature-profile:kaptDebugKotlin`: **SUCCESS**
- Ran full project build `./gradlew assembleDebug`: **SUCCESS**

> [!TIP]
> Always use `api` for libraries whose annotations or types are part of your module's public interface (like Retrofit interfaces). This prevents "NonExistentClass" errors during annotation processing in downstream modules.
