package testCase;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class YandexPage {
    public static SelenideElement findButton() {
        return $x("//button[.='Найти']");
    }
}
