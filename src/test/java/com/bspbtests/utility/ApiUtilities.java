package com.bspbtests.utility;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtilities {

    public static Response getRequest(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public static <T> T parseResponseAs(Response response, Class<T> targetClass) {
        return response.as(targetClass);
    }
}
