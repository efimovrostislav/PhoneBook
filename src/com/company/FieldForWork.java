package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FieldForWork {
    static Pattern numberTextPattern = Pattern.compile("[^\\d-+]");
    static Pattern nameTextPattern = Pattern.compile("[^a-zA-Zа-яёА-ЯЁ ]");
    static String number = " ";
    static String name = " ";
    static String nextTrip = " ";
    static int count = 0;
    static Map<String, String> mapPhoneBook = new HashMap<>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
}
