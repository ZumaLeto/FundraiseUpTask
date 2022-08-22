package page;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class PersonalInformationPage {
    FrameLocator frame;

    public PersonalInformationPage(Page page) {
        this.frame = page.frameLocator("//iframe[@title='Donation Widget']");
    }

    public void fillPersonalInformationData(String firstName, String lastName, String email) {
        frame.locator("//input[@name='firstName']").fill(firstName);
        frame.locator("//input[@name='lastName']").fill(lastName);
        frame.locator("//div[@class='group']/input[@name='email']").fill(email);
    }

    public void clickDonateButton() {
        frame.locator("(//button[contains(@class, 'btn-payment')])[last()]").click();
    }
}
