package page;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class PaymentOptionPage {
    FrameLocator frame;

    public PaymentOptionPage(Page page) {
        this.frame = page.frameLocator("//iframe[@title='Donation Widget']");
    }

    public void unCheckCommission() {
        frame.locator("//div[@role='checkbox']").uncheck();
    }

    public void chooseCreditCard() {
        frame.locator("//div[text()='Credit card']").click();
    }
}
