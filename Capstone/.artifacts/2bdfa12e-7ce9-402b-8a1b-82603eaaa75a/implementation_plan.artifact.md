# Fix Gradle Sync Error: "Cannot add extension with name 'kotlin'"

The project fails to sync because of a conflict in the `kotlin` extension registration. This is likely caused by:
1. **Incorrect Plugin Order**: In `app/build.gradle.kts`, the `kotlin-compose` compiler plugin is applied before the `kotlin-android` plugin.
2. **Version Inconsistency**: The project uses multiple versions of Kotlin and AGP across different build files and `libs.versions.toml`. Specifically, `kotlin.compose` uses `2.2.10` while `kotlin.android` uses `2.0.21`.

## Proposed Changes

### 1. Unified Dependency Management ([libs.versions.toml](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/gradle/libs.versions.toml))
- Correct the `agp` version to `8.7.3` (matching the library plugin version used in the project).
- Correct the `kotlin` version to `2.0.21` (matching the version used in the root build file).
- Add missing plugin definitions for `kotlin-android`, `kotlin-library`, `kapt`, and `hilt`.

### 2. Root Build Configuration ([build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/build.gradle.kts))
- Use aliases for all plugins instead of hardcoded IDs and versions.
- Ensure all subprojects use consistent plugin versions via the `plugins` block.

### 3. Module Build Fixes
- **[app/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/build.gradle.kts)**:
    - Reorder plugins: Apply `android-application` first, then `kotlin-android`, then `kotlin-compose`.
    - Fix the `compileSdk` syntax from `release(37)` to `37`.
- **[feature-profile/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/feature-profile/build.gradle.kts)**:
    - Use aliases for plugins.
    - Fix `compileSdk` syntax.
- **[core-network/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/core-network/build.gradle.kts)**:
    - Use aliases for plugins.

## Verification Plan

### Automated Tests
- Run `./gradlew sync` (or equivalent IDE sync) to verify the extension conflict is resolved.
- Run `./gradlew assembleDebug` to ensure the project builds correctly with the unified versions.

### Manual Verification
- Verify in Android Studio that the project structure is correctly recognized and there are no sync errors.
