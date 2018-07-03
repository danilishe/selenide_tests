package testCase;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.StringUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PenIoTest {
    public static final String TEST_PASS = "testingPageForTest";
    public static final SelenideElement passwordField = $("#password");
    public static final SelenideElement newPageNameField = $("#url");
    public static final SelenideElement submitButton = $x(".//input[@type='submit']");
    public static final SelenideElement titleField = $("#title");
    public static final SelenideElement mainTextField = $("#text");

    private static final String TEST_PAGE = "http://testingpagefortest.pen.io/";
    public static final String TEST_URL = TEST_PAGE + "edit";
    public static final SelenideElement testPageTitle = $("h1");
    public static final SelenideElement testPageText = $(".text p");
    protected WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        Configuration.browser = "chrome";
    }

    @Test
    public void pageCreationTest() {
        open("http://pen.io/");

        String url = StringUtils.getRandomString(15);
        String pass = StringUtils.getRandomString(20);

        newPageNameField.sendKeys(url);
        passwordField.sendKeys(pass);
        submitButton.click();

        Wait().withTimeout(Duration.ofSeconds(5));

        switchTo().defaultContent();
        switchTo().frame($x(".//iframe[contains(@title, 'recaptcha')]"));
        $("#rc-imageselect").shouldBe(visible);
    }

    @Test
    public void pageEditingTest() {
        open(TEST_URL);
        passwordField.sendKeys(TEST_PASS);
        submitButton.click();

        titleField.shouldBe(visible);
        mainTextField.shouldBe(visible);
    }

    @Test
    public void pagePublishingTest() {
        pageEditingTest();

        String title = StringUtils.getRandomString(50);
        String text = StringUtils.getRandomString(500);


        titleField.clear();
        titleField.sendKeys(title);

        mainTextField.clear();
        mainTextField.sendKeys(text);

        submitButton.click();

        open(TEST_PAGE);

        testPageTitle.shouldHave(Condition.matchesText(title));
        testPageText.shouldHave(Condition.matchesText(text));
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.close();
    }
}
