# Task Checklist - Fix Login and Home Page Data

- `[x]` Update Navigation to handle `customerId` parameter
    - `[x]` Update routes in `AppNavigation.kt`
    - `[x]` Update `onLoginSuccess` in `AppNavigation.kt`
    - `[x]` Fix `NavigationBar` selection logic
- `[x]` Update `HomeScreen` to trigger data load
    - `[x]` Add `customerId` parameter to `HomeScreen` composable
    - `[x]` Add `LaunchedEffect` to trigger `loadCustomer`
- `[x]` Verify the fix
    - `[x]` Check manual login flow
