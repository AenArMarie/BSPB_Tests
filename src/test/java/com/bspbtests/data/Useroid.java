package com.bspbtests.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Builder
@EqualsAndHashCode
@Getter
@Setter
@ToString
@JsonDeserialize(builder = Useroid.UseroidBuilder.class)
public class Useroid {
    private int age;
    private String name;
    private String surname;
    private String lastName;
    private String gender;
    private boolean isActiveClient;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UseroidBuilder {
    }
}
