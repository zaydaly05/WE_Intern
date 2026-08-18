# Walkthrough - Fix Coroutines Unresolved Reference

I have fixed the "Unresolved reference 'launch'" error by adding the necessary coroutine dependencies and refactoring the code to use modern Android coroutine patterns.

## Changes Made

### 1. Build Configuration
- Added `kotlinx-coroutines-android` version `1.9.0` to [libs.versions.toml](file:///D:/Desktop/WE_Intern/Week%205%20Task%202/NetworkingAndInterceptors/gradle/libs.versions.toml).
- Added the dependency to [app/build.gradle.kts](file:///D:/Desktop/WE_Intern/Week%205%20Task%202/NetworkingAndInterceptors/app/build.gradle.kts).

### 2. MainActivity Refactoring
- Added missing `import kotlinx.coroutines.launch` to [MainActivity.kt](file:///D:/Desktop/WE_Intern/Week%205%20Task%202/NetworkingAndInterceptors/app/src/main/java/com/example/networkingandinterceptors/MainActivity.kt).
- Replaced the discouraged `GlobalScope.launch` with `rememberCoroutineScope().launch`, which is better suited for Compose-based UIs and avoids memory leaks.

## Verification Results

### Automated Tests
- Successfully ran `./gradlew :app:compileDebugKotlin`. The project now builds without errors.

> [!TIP]
> Always prefer using `rememberCoroutineScope()` or `LaunchedEffect` in Compose instead of `GlobalScope` to ensure that coroutines are properly cancelled when the Composable leaves the composition.
