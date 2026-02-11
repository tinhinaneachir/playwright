package tp;

import com.microsoft.playwright.*;
import org.junit.Test;
public class Exo03Test {


    @Test
    public void testGestionAttente() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");

            page.click("#start button");
            Locator helloText = page.locator("#finish h4");
            helloText.waitFor();
            if (helloText.textContent().contains("Hello World!")) {
                System.out.println("Test réussi : Hello World affiché");
            } else {
                System.out.println("Test échoué : Hello World non affiché");
            }
            browser.close();
        }
    }
}
