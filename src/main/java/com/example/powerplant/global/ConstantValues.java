package com.example.powerplant.global;

import java.util.Random;

public class ConstantValues {
    public static final Integer SERVICE_OK = 200;
    public static final Integer SERVICE_BAD_REQUEST = 400;
    public static final Integer SERVICE_UNAUTHORIZED = 401;
    public static final Integer SERVICE_NOT_FOUND = 404;
    public static final Integer SERVICE_INTERNAL_SERVER = 500;
    public static final String SERVICE_SUCCESS_STATUS = "successful";
    public static final String SERVICE_UNSUCCESS_STATUS = "unsuccessful";
    public static final String emailRegex = "^(.+)@(.+)$";
    public static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
}