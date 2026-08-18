# Implementation Plan - Fix Manifest Merger Error (Missing Dot in Package Name)

The package name `capstone` is causing a Manifest merger failure because it lacks at least one '.' (dot) character. Android requires package names/application IDs to have at least two segments (e.g., `com.capstone`).

Additionally, we need to ensure this change resolves the original Hilt build error: `Could not find class file for 'capstone.CapstoneApplication'`.

## Proposed Changes

I will refactor the project to use the package `com.capstone`. This satisfies the Android requirement and remains close to the project's root name.

### 1. Source Code Refactoring
Move all files and update package declarations from `capstone` to `com.capstone`.

#### [MODIFY] [CapstoneApplication.kt](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/src/main/java/capstone/CapstoneApplication.kt)
- Update package to `com.capstone`.
- Move file to `app/src/main/java/com/capstone/CapstoneApplication.kt`.

#### [MODIFY] [MainActivity.kt](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/src/main/java/capstone/MainActivity.kt)
- Update package to `com.capstone`.
- Update imports for theme and feature components.
- Move file to `app/src/main/java/com/capstone/MainActivity.kt`.

#### [MODIFY] [ui/theme/](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/src/main/java/capstone/ui/theme)
- Update package to `com.capstone.ui.theme` in all files.
- Move directory to `app/src/main/java/com/capstone/ui/theme`.

#### [MODIFY] [core-network/](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/core-network/src/main/java/capstone/core/network)
- Update package to `com.capstone.core.network` and subpackages.
- Move directory to `core-network/src/main/java/com/capstone/core/network`.

#### [MODIFY] [feature-profile/](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/feature-profile/src/main/java/capstone/feature/profile)
- Update package to `com.capstone.feature.profile` and subpackages.
- Move directory to `feature-profile/src/main/java/com/capstone/feature/profile`.

### 2. Module Refactoring
Update `namespace` and `applicationId` to include the required dot.

#### [MODIFY] [app/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/build.gradle.kts)
- Change `namespace` to `com.capstone`.
- Change `applicationId` to `com.capstone`.

#### [MODIFY] [core-network/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/core-network/build.gradle.kts)
- Change `namespace` to `com.capstone.core.network`.

#### [MODIFY] [feature-profile/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/feature-profile/build.gradle.kts)
- Change `namespace` to `com.capstone.feature.profile`.

### 3. Manifest Update
#### [MODIFY] [AndroidManifest.xml](file:///D:/Desktop/WE_Intern/Week 5 Task 1/Capstone/app/src/main/AndroidManifest.xml)
- Update `android:name` for `CapstoneApplication` to `com.capstone.CapstoneApplication`.
- Update `android:name` for `MainActivity` to `com.capstone.MainActivity`.

## Verification Plan

### Automated Tests
- Run `./gradlew clean` to ensure all stale generated code is removed.
- Run `./gradlew :app:hiltJavaCompileDebug` to verify that Hilt can find the application class.
- Run `./gradlew assembleDebug` to verify the full build and Manifest merger.

### Manual Verification
- Verify that the app still works correctly after the package rename.
