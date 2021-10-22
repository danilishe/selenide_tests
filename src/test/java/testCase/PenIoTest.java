package testCase;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PenIoTest {

    public static final SelenideElement searchField = $x("//input[@id='text']");

    @Test
    public void pageCreationTest() {
        Selenide.open("https://yandex.ru");
        searchField.shouldBe(visible)
                .val("Selenide");
        YandexPage.findButton()
                .should(visible, Duration.ofSeconds(10))
                .click();

        YandexPage.findButton().should(have(text("Найти")));

        $$(".serp-item").shouldBe(CollectionCondition.sizeGreaterThan(10), Duration.ofSeconds(10));
    }
}
