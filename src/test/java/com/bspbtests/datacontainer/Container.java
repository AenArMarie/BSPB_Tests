package com.bspbtests.datacontainer;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * Хранилище данных, передаваемых между шагами
 */
@Getter
@Setter
public class Container {
    private Response response;
}
