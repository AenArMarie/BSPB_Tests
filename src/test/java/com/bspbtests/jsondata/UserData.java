package com.bspbtests.jsondata;

import com.bspbtests.data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Запись для списка пользователей
 */
@Getter
@Setter
public class UserData {

    ArrayList<User> users;
}
