import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    @Before
    public void setUp() {
        // Read headless option from system property or default to headed (false) to let the user see the browser run
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        playwright = Playwright.create();
        
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(800);

        System.out.println("Launching native Playwright Firefox browser...");
        browser = playwright.firefox().launch(options);
        
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 720));
        
        page = context.newPage();
        
        // Stealth scripts to bypass Cloudflare automation/webdriver fingerprint detection
        page.addInitScript("delete Object.getPrototypeOf(navigator).webdriver;");
        page.addInitScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined});");
    }

    @After
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    public static Page getPage() {
        return page;
    }
}
