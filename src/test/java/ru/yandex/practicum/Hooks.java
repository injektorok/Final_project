package ru.yandex.practicum;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before(order = 1)
    public void beforeScenario() {
        // Базовая настройка уже выполнена в конструкторе TestContext
    }

    @After
    public void afterScenario() {
        context.cleanup();
    }
}