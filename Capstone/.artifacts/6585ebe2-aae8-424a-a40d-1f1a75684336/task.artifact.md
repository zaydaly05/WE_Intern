# Task List - Fix AGP 9.0 Built-in Kotlin Conflict

- `[x]` Update `libs.versions.toml` to include `legacy-kapt` and remove `kotlin-android`/`kotlin-kapt`
- `[x]` Update root `build.gradle.kts` to remove `kotlin-android` and switch to `legacy-kapt`
- `[x]` Update `app/build.gradle.kts` to remove `kotlin-android` and switch to `legacy-kapt`
- `[x]` Update `core-network/build.gradle.kts` to remove `kotlin-android` and switch to `legacy-kapt`
- `[x]` Update `feature-profile/build.gradle.kts` to remove `kotlin-android` and switch to `legacy-kapt`
- `[x]` Verify by running Gradle Sync
