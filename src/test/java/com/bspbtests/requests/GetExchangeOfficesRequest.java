package com.bspbtests.requests;

import com.bspbtests.utility.ApiUtilities;
import com.utility.files.FilesReader;
import io.restassured.response.Response;

public class GetExchangeOfficesRequest {

    private static final String URL = "https://www.bspb.ru/api/currency-service/office-rates";

    public static Response performGet() {
        return ApiUtilities.getRequest(URL);
    }
}
