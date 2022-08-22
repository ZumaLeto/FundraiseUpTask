package page;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class SecureDonationPage {
    FrameLocator frame;

    public SecureDonationPage(Page page) {
        this.frame = page.frameLocator("//iframe[@title='Donation Widget']");
    }

    public void clickMonthly() {
        frame.locator("//div[text()='Monthly']").click();
    }

    public void selectCurrencyOption(String currency) {
        frame.locator("//select[@data-qa='currency-selector']").selectOption(currency);
    }

    public void fillPriceField(String amount) {
        frame.locator("//input[contains(@class,'price-control')]").evaluate("document.querySelector(\".price-control\").value='';");
        frame.locator("//input[contains(@class,'price-control')]").fill(amount);
    }

    public void clickDonateMonthly() {
        frame.locator("//div[text()='Donate monthly']").click();
    }

}
