package page;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;

public class CreditCardPage {
    FrameLocator frame;
    Page page;

    public CreditCardPage(Page page) {
        this.page = page;
        this.frame = page.frameLocator("//iframe[@title='Donation Widget']");
    }

    public void fillCardData(String cardNumber, String expDate, String cvc) {
        frame = page.frameLocator("//iframe[@title='Donation Widget']").frameLocator("//iframe[@title='Secure card number input frame']");
        frame.locator("//input[@placeholder='Card number']").fill(cardNumber);
        frame = page.frameLocator("//iframe[@title='Donation Widget']").frameLocator("//iframe[@title='Secure expiration date input frame']");
        frame.locator("//input[@placeholder='MM / YY']").fill(expDate);
        frame = page.frameLocator("//iframe[@title='Donation Widget']").frameLocator("//iframe[@title='Secure CVC input frame']");
        frame.locator("//input[@placeholder='CVC']").fill(cvc);
        frame = page.frameLocator("//iframe[@title='Donation Widget']");
    }

    public void clickContinue() {
        frame.locator("//div[contains(text(),'Continue')]").click();
    }

    public void checkError() {
        Assertions.assertEquals(frame.locator("//div[@data-qa='card-continue-error-message']").textContent(),
                "This could be due to any of several reasons: incorrect security code, insufficient funds, card limit, card disabled, etc.");

    }
}
