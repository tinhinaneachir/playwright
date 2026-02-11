package tp;

import com.microsoft.playwright.*;
import org.junit.Test;
import java.util.logging.Logger;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class Exo05Test {
    public static Logger logger = Logger.getLogger(Exo05Test.class.getName());

    @Test
    public void testSauceDemo() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://saucedemo.com/");
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");

        System.out.println("✅ Login réussi");
        Locator products = page.locator(".inventory_item");
        assertThat(products).hasCount(6);
        logger.info("Nombre de produits affichés : " + products.count());


        page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText("Sauce Labs Backpack"))
                .locator("button")
                .click();

        page.click(".shopping_cart_link");


        Locator cartBadge = page.locator(".shopping_cart_badge");
        assertThat(cartBadge).hasText("1");
        logger.info("Nombre d'articles dans le panier : " + cartBadge.textContent());

        page.click("#checkout");

        page.fill("#first-name", "Tina");
        page.fill("#last-name", "Achir");
        page.fill("#postal-code", "95130");

        page.click("#continue");
        assertThat(page).hasURL("https://www.saucedemo.com/checkout-step-two.html");
        assertThat(page.locator(".title")).hasText("Checkout: Overview");
        logger.info("URL actuelle : " + page.url());

        page.click("#finish");
        assertThat(page.locator(".complete-header")).hasText("Thank you for your order!");
        logger.info("Message de confirmation : " + page.locator(".complete-header").textContent());

        browser.close();

    }
}
