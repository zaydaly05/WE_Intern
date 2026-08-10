# Walkthrough - Logout Functionality

I have successfully added logout functionality to the application, accessible from both the Home screen header and the Profile screen.

## Changes Made

### UI Components

#### [WEHeader.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/ui/components/WEHeader.kt)
- Updated the shared header component to include an optional logout icon button.
- Used `Icons.AutoMirrored.Filled.ExitToApp` for better RTL support.

### Presentation

#### [HomeScreen.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/home/HomeScreen.kt)
- Added a logout icon button to the custom header row, positioned next to the profile icon.

#### [ProfileScreen.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/profile/ProfileScreen.kt)
- Added a full-width "Logout" button at the bottom of the screen with a distinct error-colored theme.

### Navigation

#### [AppNavigation.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/navigation/AppNavigation.kt)
- Implemented the `logout` logic:
    - Clears the internal `customerIdState`.
    - Navigates to the `login` screen.
    - Clears the entire navigation backstack (`popUpTo(0)`) to ensure security.
- Wired the `logout` action to `HomeScreen` and `ProfileScreen`.

## Verification Results

### Automated Tests
- Executed `:app:assembleDebug` to verify the project builds successfully.

### Manual Verification
- Logout from Home header: Navigates to Login, clears state.
- Logout from Profile button: Navigates to Login, clears state.
- Backstack check: Pressing 'Back' from Login screen after logout exits the app instead of returning to Home.
