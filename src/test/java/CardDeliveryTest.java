

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @Test
    void shouldInputIsCorrect(){
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").setValue("localDate+3");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Забронировать")).waitUntil(visible,15000);
        $(withText("Успешно!"));


    }

}


