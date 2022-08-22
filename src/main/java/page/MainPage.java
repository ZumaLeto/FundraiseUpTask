package page;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class MainPage {
    Frame frame;

    public MainPage(Page page) {
        this.frame = page.frame("XBGSFAMB");
    }

    public void clickDonateButton() {
        frame.locator("//div[@class='button-hover-bg']").click();
    }
}
