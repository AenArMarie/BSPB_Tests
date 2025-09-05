package com.bspbtests.steps;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.data.User;
import com.bspbtests.data.offices.ExchangeOfficeModel;
import com.bspbtests.data.offices.OfficeDataModel;
import com.bspbtests.datacontainer.Container;
import com.bspbtests.jsondata.UserData;
import com.bspbtests.requests.ProjectRequests;
import com.bspbtests.utility.AllureUtilities;
import com.bspbtests.utility.ApiUtilities;
import com.bspbtests.utility.dataprocessing.FilesReader;
import com.bspbtests.utility.dataprocessing.ProjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

public class CollectionSteps {
    private final Container context;

    public CollectionSteps(Container container) {
        context = container;
    }

    @Тогда("количество пользователей равно {int} и ни один из них не имеет возраст {int}")
    public void checkSelfAwareness(int amount, int age) {
        UserData userData = FilesReader.readJson(PathConstants.USER_DATA_PATH, UserData.class);
        assertThat(userData)
                .as("Проверка данных на null")
                .isNotNull()
                .as("Не имеет null параметров")
                .hasNoNullFieldsOrProperties()
                .extracting(UserData::getUsers)
                .asInstanceOf(list(User.class))
                .as("Имеет размер %d", amount) //TODO
                .hasSize(amount)
                .as("Не имеет пользователей с возрастом %d", age)
                .filteredOn(user -> user.getAge() == age)
                .isEmpty();
    }

    @Тогда("коллекция по запросу офисов обмена валюты содержит участки с именами:")
    public void checkExchangeOfficesAPIContains(DataTable table) {
        Response getExchangeOfficesResponse = ProjectRequests.getOfficesExchangeRates();
        assumeThat(getExchangeOfficesResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllureUtilities.attachJson("Response json", getExchangeOfficesResponse);
        OfficeDataModel offices = ApiUtilities.parseResponseAs(getExchangeOfficesResponse, OfficeDataModel.class);
        List<String> expectedNames = table.asList(String.class);
        assertThat(offices.items())
                .as("Проверка наличия нужных имен пунктов обмена")
                .extracting(ExchangeOfficeModel::name)
                .containsAll(expectedNames);
    }

    @Когда("пользователь выполняет запрос на получение офисов")
    public void sendOfficesRequest() {
        Response getExchangeOfficesResponse = ProjectRequests.getOfficesExchangeRates();
        assumeThat(getExchangeOfficesResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllureUtilities.attachJson("Response json", getExchangeOfficesResponse);
        context.setResponse(getExchangeOfficesResponse);
    }

    @Когда("пользователь выполняет запрос на курса обмена для карты {string}")
    public void sendCardsExchangeRateRequest(String cardName) {
        Response getCardExchangeRateResponse = ProjectRequests.getExchangeRateByCard(cardName);
        AllureUtilities.attachJson("Response json", getCardExchangeRateResponse);
        context.setResponse(getCardExchangeRateResponse);
    }

    @Тогда("ответ на запрос совпадает с ответом по относительному пути {string}")
    public void checkOfficeNames(String path) {
        assumeThat(context.getResponse()).isNotNull();
        String expectedJson = FilesReader.readJsonAsString(path);
        assumeThat(expectedJson).isNotNull();
        JsonNode actualNode = ProjectMapper.mapJson(context.getResponse().asString());
        JsonNode expectedNode = ProjectMapper.mapJson(FilesReader.readJsonAsString(path));
        assertThat(actualNode)
                .as("Проверка равенства json")
                .usingRecursiveComparison()
                .isEqualTo(expectedNode);
    }

    @Тогда("этот запрос содержит участки с именами:")
    public void checkOfficeNames(DataTable table) {
        OfficeDataModel offices = ApiUtilities.parseResponseAs(context.getResponse(), OfficeDataModel.class);
        List<String> expectedNames = table.asList(String.class);
        assertThat(offices.items())
                .as("Проверка наличия нужных имен пунктов обмена")
                .extracting(ExchangeOfficeModel::name)
                .containsAll(expectedNames);
    }
}
