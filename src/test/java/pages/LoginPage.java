package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== Correct Locators =====
    private final By emailInput = By.id("formEmail");
    private final By passwordInput = By.id("formPassword");
    private final By loginBtn = By.cssSelector("button.login-button");
    private final By eyeToggle = By.cssSelector("img.passowrd-visible");

    // Updated locator for invalid credential error
    private final By invalidCredentialMsg = By.cssSelector("div.invalid-credential-div p.normal-text");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Check if page is loaded properly
    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("janitri") || driver.getTitle().length() > 0;
    }

    // Enter email
    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Enter password
    public void enterPassword(String pwd) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(pwd);
    }

    // Click login button
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    // Get error message if present
    public String getErrorTextIfAny() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialMsg));
            return errorElement.getText().trim();
        } catch (TimeoutException e) {
            System.out.println("⚠️ No error message found on login attempt.");
            return "";
        }
    }

    // Check if password is masked
    public boolean isPasswordMasked() {
        WebElement field = driver.findElement(passwordInput);
        return "password".equalsIgnoreCase(field.getAttribute("type"));
    }

    // Toggle password visibility
    public void togglePasswordVisibility() {
        wait.until(ExpectedConditions.elementToBeClickable(eyeToggle)).click();
    }

    // Check if login button is enabled
    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginBtn).isEnabled();
    }

    // Check if all core elements are present
    public boolean areCoreElementsPresent() {
        try {
            driver.findElement(emailInput);
            driver.findElement(passwordInput);
            driver.findElement(loginBtn);
            driver.findElement(eyeToggle);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Get page title
    public String getPageTitle() {
        return driver.getTitle();
    }
}
