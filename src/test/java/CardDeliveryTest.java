
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @Test
    void shouldInputIsCorrect() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Забронировать")).waitUntil(visible, 15000);
        $(withText("Успешно!"));
    }

    @Test
    void shouldPhoneNotCorrect() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("89098765432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(By.className("input_invalid")).shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        $("[data-test-id='phone']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNameNotCorrect() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Kopatilov Andrey");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(By.className("input_invalid")).shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldChecboxNotClick() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement]").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
        $(By.className("input_invalid"));
    }

    @Test
    void shouldCityNotSelected() {
        open("http://localhost:9999");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(By.className("input_invalid")).shouldHave(text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldCityNotUnavailable() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Бикин");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(By.className("input_invalid")).shouldHave(text("Доставка в выбранный город недоступна"));
    }

}





