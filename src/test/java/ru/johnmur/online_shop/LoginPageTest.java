package ru.johnmur.online_shop;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPageTest {

    @BeforeEach
    public void setUp() {
        // Установка базового URL
        Configuration.baseUrl = "http://localhost:3033"; // Замените на URL вашего приложения
        open("/login"); // Открываем страницу логина
    }

    @Test
    public void loginPageShouldBeAccessible() {
        // Проверка, что заголовок страницы содержит "Login"
        $("title").shouldHave(attribute("text", "Login"));

        // Проверка, что форма логина доступна
        $("form").shouldBe(visible);

        // Проверка наличия поля ввода имени пользователя
        $("#username").shouldBe(visible);

        // Проверка наличия поля ввода пароля
        $("#password").shouldBe(visible);

        // Проверка наличия кнопки "Login"
        $("button[type='submit']").shouldHave(text("Login"));
    }

    @Test
    public void shouldAllowUserToLogin() {
        // Ввод корректного имени пользователя
        $("#username").setValue("admin");

        // Ввод корректного пароля
        $("#password").setValue("admin");

        // Нажатие на кнопку "Login"
        $("button[type='submit']").click();

        // Проверка, что после входа отображается приветственное сообщение или другая ожидаемая часть интерфейса
        $("a.account-button")
                .shouldBe(visible)
                .shouldHave(attributeMatching("href", ".*\\/account$")) // Проверка, что href заканчивается на "/account"
                .shouldHave(text("Личный кабинет"));    }

//    @Test
//    public void shouldShowErrorForInvalidCredentials() {
//        // Ввод неверного имени пользователя
//        $("#username").setValue("invalidUser");
//
//        // Ввод неверного пароля
//        $("#password").setValue("invalidPassword");
//
//        // Нажатие на кнопку "Login"
//        $("button[type='submit']").click();
//
//        // Проверка, что отображается сообщение об ошибке (например, "Invalid username or password")
//        $(".alert-danger").shouldHave(text("Invalid username or password"));
//    }
}
