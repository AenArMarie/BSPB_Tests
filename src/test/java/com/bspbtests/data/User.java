package com.bspbtests.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record User(int age,
                   String name,
                   String surname,
                   String lastName,
                   String gender,
                   boolean isActiveClient) {
}
