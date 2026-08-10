# Implementation Plan - Fix Login and Navigation Logic

The user reported that logging in as "customer 1" takes them to the home page of "customer 25". This is caused by a failure to pass the logged-in `customerId` to the `HomeScreen` and a lack of automatic data refresh upon landing on the home page.

## User Review Required

> [!IMPORTANT]
> The navigation structure will be updated to include `customerId` as a path parameter for the `home` route. This ensures that the `HomeScreen` knows which user's data to load.

## Proposed Changes

### Navigation

#### [MODIFY] [AppNavigation.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/navigation/AppNavigation.kt)
- Update the "home" route to accept a `customerId` parameter: `"home/{customerId}"`.
- Update `onLoginSuccess` in `LoginScreen` to pass the `customerId` to the "home" route.
- Update `NavigationBar` logic to handle the parameterized "home" route.

### Home Screen

#### [MODIFY] [HomeScreen.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/home/HomeScreen.kt)
- Update `HomeScreen` to accept `customerId: String?`.
- Add a `LaunchedEffect` that calls `viewModel.loadCustomer(customerId)` when the screen is first composed with a valid `customerId`.

### Home View Model

#### [MODIFY] [HomeViewModel.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/home/HomeViewModel.kt)
- Ensure `loadCustomer` properly refreshes the customer data from the repository. (Logic already exists but is not called).

## Verification Plan

### Automated Tests
- Run the app and login as "customer 1".
- Verify that the home page displays "name 1" (ID 1).
- Refresh the data using the refresh button and verify it still shows "name 1".

### Manual Verification
- Deploy to an emulator/device and perform a login with known credentials.
- Check the `customerId` passed in the navigation logs (if any).
