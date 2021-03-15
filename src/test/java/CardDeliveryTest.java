import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    @Test
    @DisplayName("Card delivery form is filled in correctly")
    void shouldSendInvitation()  {
        open("http://localhost:9999");
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate currentDate = LocalDate.now();
        LocalDate calculateDate = currentDate.plusDays(3);
        String meetingDate = calculateDate.format(formatter);
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").setValue(meetingDate);
        form.$("[data-test-id=name] input").setValue("Иван Петров");
        form.$("[data-test-id=phone] input").setValue("+79605550012");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);

    }

    @Test
    @DisplayName("Card delivery form has any mistakes")
    void shouldNotSendInvitation()  {
        open("http://localhost:9999");
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate currentDate = LocalDate.now();
        LocalDate calculateDate = currentDate.plusDays(3);
        String meetingDate = calculateDate.format(formatter);
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").setValue(meetingDate);
        form.$("[data-test-id=name] input").setValue("Иван Петров");
        form.$("[data-test-id=phone] input").setValue("+796055500120");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(visible, 15000);
    }
}