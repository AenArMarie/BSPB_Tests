package com.bspbtests.steps;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.data.User;
import com.bspbtests.jsondata.testdata.TestData;
import com.utility.files.FilesReader;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;

import static com.bspbtests.steps.Hooks.testData;
import static org.assertj.core.api.Assertions.*;

public class CollectionSteps {

    @Когда("пользователь отходит от монитора")
    @Step("Пользователь отошел от монитора")
    public void getAway() {

    }

    @Тогда("его воображаемое самопредставление совпадает с тестовыми данными")
    @Step("Проверка совпадения самопредставления пользователя с тестовыми данными")
    public void checkSelfAwareness() {
        User actualUser = new User(24,
                "valery",
                "victorovich",
                "whoshich",
        "M",
                false);
        assertThat(actualUser).as("Проверка сравнения пользователей").isEqualTo(testData.getUserData());
    }
}
