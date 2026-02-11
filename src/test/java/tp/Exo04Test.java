package tp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Test;

public class Exo04Test {

@Test
    public void testPlusieursOnglets() {
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false)
    );
    Page page = browser.newPage();
    page.navigate("https://the-internet.herokuapp.com/windows");
    page.click("a[href='/windows/new']");
    System.out.println("Onglet 1 : " + page.title());

    browser.close();
}
}
