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
    public static final String firstNameRegex = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
    public static String codeGenerator4Digit() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++)
            sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();
    }
    public static final String LinkForResetPasswordOTP = "http://192.168.18.154:8080/pages/verify-pin/";
}