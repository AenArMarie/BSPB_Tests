package com.bspbtests.steps;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.data.ExchangeOfficeModel;
import com.bspbtests.data.OfficeDataModel;
import com.bspbtests.data.Useroid;
import com.bspbtests.jsondata.UserData;
import com.bspbtests.requests.GetExchangeOfficesRequest;
import com.bspbtests.utility.AllureUtilities;
import com.bspbtests.utility.ApiUtilities;
import com.utility.files.FilesReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

public class CollectionSteps {

    @Тогда("воображаемое самопредставление пользователя совпадает с тестовыми данными")
    public void checkSelfAwareness() {
        SoftAssertions softly = new SoftAssertions();
        Useroid actualUseroid = Useroid.builder().
                age(24).
                name("valery").
                surname("victorovich").
                lastName("whosich").
                gender("M").
                isActiveClient(false).
                build();
        Useroid expectedUseroid = Useroid.builder().
                age(25).
                name("valery").
                surname("victorovich").
                lastName("whosich").
                gender("M").
                isActiveClient(false).
                build();
        softly.assertThat(actualUseroid).usingRecursiveComparison().isNotEqualTo(expectedUseroid);
        UserData userData = FilesReader.readJson(PathConstants.USER_DATA_PATH, UserData.class);
        assertThat(userData).isNotNull();
        softly.assertThat(userData.getUsers()).usingRecursiveFieldByFieldElementComparator().contains(actualUseroid);
        softly.assertThat(userData.getUsers()).hasSize(4).usingRecursiveFieldByFieldElementComparator().doesNotContain(expectedUseroid);
        softly.assertThat(userData.getUsers()).filteredOn(useroid -> useroid.getAge() == 25).isEmpty();
        softly.assertAll();
    }

    @Тогда("коллекция по запросу соответствует требованиям")
    public void checkApi() {
        SoftAssertions softly = new SoftAssertions();
        AllureUtilities.attachJson("Expected json", PathConstants.OFFICES_DATA_PATH);
        OfficeDataModel expectedOffices = FilesReader.readJson(PathConstants.OFFICES_DATA_PATH, OfficeDataModel.class);
        String expectedJson = FilesReader.readFileAsString(PathConstants.OFFICES_DATA_PATH);
        assumeThat(expectedJson).isNotNull();
        expectedJson = expectedJson.replaceAll("\\s+", "");
        Response getExchangeOfficesResponse = GetExchangeOfficesRequest.performGet();
        assumeThat(getExchangeOfficesResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllureUtilities.attachJson("Response json", getExchangeOfficesResponse);
        OfficeDataModel offices = ApiUtilities.parseResponseAs(getExchangeOfficesResponse, OfficeDataModel.class);

        softly.assertThat(offices.items()).
                filteredOn(office -> office.address().contains("England")).
                isEmpty();
        softly.assertThat(offices.items())
                .as("Проверка наличия нужных имен пунктов обмена")
                .extracting(ExchangeOfficeModel::name)
                .contains("ДО \"Гаванский\"", "ДО \"Пушкинский\"", "ДО \"Тосненский\"");
        softly.assertThat(offices).usingRecursiveComparison().isEqualTo(expectedOffices);
        softly.assertThat(getExchangeOfficesResponse.getBody().asString().replaceAll("\\s+", "")).
                as("Проверка равенства строк json-файлов").
                withFailMessage("Json-файлы не равны").
                isEqualTo(expectedJson);
        softly.assertAll();
    }

    @Тогда("коллекция по запросу офисов обмена валюты содержит участки с именами:")
    public void checkExchangeOfficesAPIContains(DataTable table) {
        Response getExchangeOfficesResponse = GetExchangeOfficesRequest.performGet();
        assumeThat(getExchangeOfficesResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllureUtilities.attachJson("Response json", getExchangeOfficesResponse);
        OfficeDataModel offices = ApiUtilities.parseResponseAs(getExchangeOfficesResponse, OfficeDataModel.class);
        List<String> expectedNames = table.asList(String.class);
        assertThat(offices.items())
                .as("Проверка наличия нужных имен пунктов обмена")
                .extracting(ExchangeOfficeModel::name)
                .containsAll(expectedNames);
    }
}
