package com.bspbtests.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Useroid {
    int age;
    String name;
    String surname;
    String lastName;
    String gender;
    boolean isActiveClient;

    @JsonCreator
    public Useroid(@JsonProperty("age") int age,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("lastName")String lastName,
                   @JsonProperty("gender") String gender,
                   @JsonProperty("isActiveClient") boolean isActiveClient) {
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.gender = gender;
        this.isActiveClient = isActiveClient;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Useroid useroid = (Useroid) o;
        return age == useroid.age && isActiveClient == useroid.isActiveClient && Objects.equals(name, useroid.name) && Objects.equals(surname, useroid.surname) && Objects.equals(lastName, useroid.lastName) && Objects.equals(gender, useroid.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, surname, lastName, gender, isActiveClient);
    }
}
