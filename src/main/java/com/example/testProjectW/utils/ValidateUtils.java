package com.example.testProjectW.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    public static boolean isDigits(String input) {
        Pattern pattern = Pattern.compile("[+฿/!@#$%^&*\\\\><{}\\[\\]\":;'|,=~]+");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean prefixNumber(String input) {
        Pattern pattern = Pattern.compile("^(06|07|08|09)\\d{8}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean checkNumber(String input) {
        String regex = "[0-9]]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}