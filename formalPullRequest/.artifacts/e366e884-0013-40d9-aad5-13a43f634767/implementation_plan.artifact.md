# Add Logout Functionality

The goal is to provide a logout mechanism accessible from the Profile screen and the top header of the application.

## User Review Required

> [!IMPORTANT]
> Logout will:
> 1. Clear the `customerIdState` in the navigation scope.
> 2. Navigate back to the `login` screen.
> 3. Clear the navigation backstack to prevent the user from navigating back to protected screens.

## Proposed Changes

### [Navigation](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/navigation)

#### [MODIFY] [AppNavigation.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/navigation/AppNavigation.kt)
- Define a `logout` lambda function.
- Pass the `logout` function to `HomeScreen` and `ProfileScreen` composables in the `NavHost`.

### [Presentation - Home](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/home)

#### [MODIFY] [HomeScreen.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/home/HomeScreen.kt)
- Add `onLogout: () -> Unit` parameter.
- Add a logout `IconButton` in the Header `Row` (next to the Profile icon).

### [Presentation - Profile](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/profile)

#### [MODIFY] [ProfileScreen.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/presentation/profile/ProfileScreen.kt)
- Add `onLogout: () -> Unit` parameter.
- Add a "Logout" button at the bottom of the screen.

### [UI Components](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/ui/components)

#### [MODIFY] [WEHeader.kt](file:///D:/Desktop/WE_Intern/Week 4 Task 3/formalPullRequest/app/src/main/java/com/example/formalpullrequest/ui/components/WEHeader.kt)
- Add `onLogoutClick: (() -> Unit)? = null` parameter.
- If provided, display a logout icon button.

## Verification Plan

### Manual Verification
1.  **Home Screen Logout**:
    - Log in, click the logout icon in the header.
    - Verify navigation to Login screen and that the bottom bar disappears.
2.  **Profile Screen Logout**:
    - Log in, navigate to Profile, click the Logout button.
    - Verify navigation to Login screen and that the bottom bar disappears.
3.  **Backstack Integrity**:
    - After logging out, press the system back button.
    - Verify the app doesn't go back to the Home screen (or exits if Login is the only screen).
