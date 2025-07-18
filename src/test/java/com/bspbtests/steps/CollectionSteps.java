package com.bspbtests.steps;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.data.User;
import com.bspbtests.data.Useroid;
import com.bspbtests.jsondata.ConfigData;
import com.bspbtests.jsondata.UserData;
import com.bspbtests.jsondata.testdata.TestData;
import com.utility.files.FilesReader;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

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
        SoftAssertions softly = new SoftAssertions();
        Useroid actualUser = new Useroid(24,
                "valery",
                "victorovich",
                "whoshich",
        "M",
                false);
        Useroid expectedUser = new Useroid(24,
                "valery",
                "victorovich",
                "whoshich",
                "M",
                false);
        softly.assertThat(actualUser).isEqualTo(expectedUser);
        UserData userData = FilesReader.readJson(PathConstants.USER_DATA_PATH, UserData.class);
        softly.assertThat(userData.getUsers()).contains(actualUser);
        softly.assertAll();
    }
}
