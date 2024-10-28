package org.example.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class PostalCodeUtils {
    public static final ArrayList<String> INVALID_POSTAL_CODES = new ArrayList<>(Arrays.asList("9679", "9681", "9682"));
    public static final String POSTAL_CODE_NUMBERS_REGEX = "^\\d{4}";
    public static final String POSTAL_LETTERS_REGEX = "[A-Z]{2}";
    public static final String POSTAL_CODE_REGEX = POSTAL_CODE_NUMBERS_REGEX + POSTAL_LETTERS_REGEX;

    public static String formatPostalCode(String postalCode) {
        return postalCode.trim().replaceAll(" ", "").toUpperCase();
    }

    public static boolean isValidPostalCode(String postalCode) {
        return postalCode.matches(POSTAL_CODE_REGEX);
    }

    public static boolean isValidPostalCodeRegion(String postalCode) {
        if (!isValidPostalCode(postalCode)) {
            return false;
        }

        return !INVALID_POSTAL_CODES.contains(postalCode.split(POSTAL_LETTERS_REGEX)[0]);
    }
}
