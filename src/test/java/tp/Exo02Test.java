package tp;
import com.microsoft.playwright.*;
import org.junit.Test;


public class Exo02Test {
    @Test
    public void testLogin() {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();

        page.navigate("https://the-internet.herokuapp.com/login");
        page.fill("#username", "tomsmith");
        page.fill("#password", "superSecretPassword!!");
        page.click("button[type='submit']");

        String message = page.textContent("#flash");

        if (message.contains("You logged into a secure area!")) {
            System.out.println("✅ Test réussi");
        } else {
            System.out.println("❌ Test échoué");
        }

        page.waitForTimeout(3000);

        browser.close();
        playwright.close();
    }
}
