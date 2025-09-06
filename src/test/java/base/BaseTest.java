package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.*;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://dev-dash.janitri.in/";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        // ✅ Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-popup-blocking");

        // ✅ Automatically allow notifications using prefs
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();
        contentSettings.put("notifications", 1); // 1 = allow, 2 = block
        profile.put("default_content_setting_values", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        // ✅ Set timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // ✅ Use Chrome DevTools Protocol to auto-grant notifications
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("origin", BASE_URL);
            params.put("permissions", Arrays.asList("notifications"));
            ((ChromeDriver) driver).executeCdpCommand("Browser.grantPermissions", params);
        } catch (Exception ignored) {
            System.out.println("⚠️ CDP grantPermissions not supported, falling back to prefs.");
        }

        // ✅ Navigate to base URL
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
