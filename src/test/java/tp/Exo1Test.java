package tp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.junit.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Exo1Test {

    @Test
    public void testExempleDomainTitle(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        var page = browser.newPage();
        page.navigate("https://example.com");
        assertThat(page).hasTitle("Example Domain");
        browser.close();
    }

}
