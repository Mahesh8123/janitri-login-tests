package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test(description = "Validate presence of UI elements on login page")
    public void testPresenceOfElements() {
        LoginPage lp = new LoginPage(driver);
        Assert.assertTrue(lp.isLoaded(), "Login page did not load.");
        Assert.assertTrue(lp.areCoreElementsPresent(), "Core elements (email, password, login, eye) are missing.");
        Assert.assertTrue(lp.getPageTitle().toLowerCase().contains("janitri") || lp.getPageTitle().length() > 0,
                "Title not visible or incorrect.");
    }

    @Test(description = "Click login with blank fields and verify error message")
    public void testLoginWithBlankFields() {
        LoginPage lp = new LoginPage(driver);

        lp.clickLogin();

        String errorMsg = lp.getErrorTextIfAny();
        System.out.println("Blank fields error: " + errorMsg);

        Assert.assertTrue(!errorMsg.isBlank(), "Expected an error message when trying to login with blank fields.");
    }

    @Test(description = "Password masking toggle works")
    public void testPasswordMaskedbutton() {
        LoginPage lp = new LoginPage(driver);

        Assert.assertTrue(lp.isPasswordMasked(), "❌ Password should be masked by default.");

        lp.togglePasswordVisibility();
        Assert.assertFalse(lp.isPasswordMasked(), "❌ Password should become visible after first toggle.");

        lp.togglePasswordVisibility();
        Assert.assertTrue(lp.isPasswordMasked(), "❌ Password should be masked again after second toggle.");
    }

    @Test(description = "Invalid login shows error message")
    public void testInvalidLoginShowErrorMsg() {
        LoginPage lp = new LoginPage(driver);

        lp.enterEmail("random_user@example.com");
        lp.enterPassword("WrongPass123!");
        lp.clickLogin();

        String err = lp.getErrorTextIfAny();
        System.out.println("⚠️ Captured invalid login error: " + err);

        Assert.assertTrue(!err.isBlank(),
                "❌ Expected an error message for invalid credentials, but none was found.");
    }
}
