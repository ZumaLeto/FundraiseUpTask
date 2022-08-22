import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.*;

public class PaymentUITests {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    String basicURL = "https://data.fundraiseup.com/qa-test-7R58U3/";

    @BeforeEach
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
    }


    @ParameterizedTest
    @CsvSource(value = {"1024,768", "428,926"})
    public void donateFailedTest(int width, int height)  {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));

        Page page = context.newPage();
        page.setDefaultTimeout(10000);
        page.navigate(basicURL);
        MainPage mainPage = new MainPage(page);
        mainPage.clickDonateButton();
        SecureDonationPage secureDonationPage = new SecureDonationPage(page);
        secureDonationPage.clickMonthly();
        secureDonationPage.selectCurrencyOption("USD");
        secureDonationPage.fillPriceField("100");
        secureDonationPage.clickDonateMonthly();
        PaymentOptionPage paymentOptionPage = new PaymentOptionPage(page);
        paymentOptionPage.unCheckCommission();
        paymentOptionPage.chooseCreditCard();
        CreditCardPage creditCardPage = new CreditCardPage(page);
        creditCardPage.fillCardData("4242424242424242", "04/24", "000");
        creditCardPage.clickContinue();
        PersonalInformationPage personalInformationPage = new PersonalInformationPage(page);
        personalInformationPage.fillPersonalInformationData("firstName", "lastName", "email@mail.com");
        personalInformationPage.clickDonateButton();
        creditCardPage.checkError();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    public void donateFailedTestDuplicate()  {
        Page page = browser.newPage();
        page.setDefaultTimeout(10000);
        page.navigate(basicURL);
        MainPage mainPage = new MainPage(page);
        mainPage.clickDonateButton();
        SecureDonationPage secureDonationPage = new SecureDonationPage(page);
        secureDonationPage.clickMonthly();
        secureDonationPage.selectCurrencyOption("USD");
        secureDonationPage.fillPriceField("100");
        secureDonationPage.clickDonateMonthly();
        PaymentOptionPage paymentOptionPage = new PaymentOptionPage(page);
        paymentOptionPage.unCheckCommission();
        paymentOptionPage.chooseCreditCard();
        CreditCardPage creditCardPage = new CreditCardPage(page);
        creditCardPage.fillCardData("4242424242424242", "04/24", "000");
        creditCardPage.clickContinue();
        PersonalInformationPage personalInformationPage = new PersonalInformationPage(page);
        personalInformationPage.fillPersonalInformationData("firstName", "lastName", "email@mail.com");
        personalInformationPage.clickDonateButton();
        creditCardPage.checkError();
    }

    @AfterEach
    void closeBrowser() {
        playwright.close();
    }

}

