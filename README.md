# Janitri Login Tests (Skeleton)

## What this contains
- Maven project skeleton with Selenium, TestNG & WebDriverManager
- Page Object (LoginPage.java)
- BaseTest (handles Chrome options + notifications)
- Basic TestNG tests (LoginTests.java)
- Manual test cases Excel (included)

## Prerequisites
- JDK 17 or newer (set JAVA_HOME)
- IntelliJ IDEA (Community is fine)
- Maven (IntelliJ bundled is ok)
- Chrome (latest)

## How to import and run
1. Open IntelliJ → File → New → Project from Existing Sources → choose this folder and import as Maven project.
2. Ensure Project SDK set to Java 17.
3. Right-click `testng.xml` → Run, or run via terminal:
   ```
   mvn -DskipTests=false test
   ```
4. Update locators in `pages/LoginPage.java` if tests fail to find elements.

## Notification handling
The BaseTest attempts to auto-allow notifications via Chrome prefs and CDP so the login page loads.

## Next steps
- Update selectors after inspecting the actual login page HTML.
- Push to GitHub and submit the repo link.
