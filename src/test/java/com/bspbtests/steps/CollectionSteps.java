package com.bspbtests.steps;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.data.ExchangeOfficeModel;
import com.bspbtests.data.OfficeDataModel;
import com.bspbtests.datacontainer.Container;
import com.bspbtests.jsondata.UserData;
import com.bspbtests.requests.GetExchangeOfficesRequest;
import com.bspbtests.utility.AllureUtilities;
import com.bspbtests.utility.ApiUtilities;
import com.bspbtests.utility.FilesReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

public class CollectionSteps {
    private final Container context;

    public CollectionSteps(Container container) {
        context = container;
    }

    @Тогда("воображаемое самопредставление пользователя совпадает с тестовыми данными")
    public void checkSelfAwareness() {
        UserData userData = FilesReader.readJson(PathConstants.USER_DATA_PATH, UserData.class);
        assumeThat(userData).
                as("Проверка userData не null").
                isNotNull().
                hasNoNullFieldsOrProperties();
        assertThat(userData.getUsers()).
                isNotNull().
                hasSize(4).
                filteredOn(useroid -> useroid.getAge() == 25).
                isEmpty();
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

    @Когда("пользователь выполняет запрос на получение офисов")
    public void sendOfficesRequest() {
        Response getExchangeOfficesResponse = GetExchangeOfficesRequest.performGet();
        assumeThat(getExchangeOfficesResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllureUtilities.attachJson("Response json", getExchangeOfficesResponse);
        context.setResponse(getExchangeOfficesResponse);
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
