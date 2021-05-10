package com.soulroomie.demo.tools;

import java.util.HashMap;
import java.util.Map;

public class Data {

    public static final Map<String, Integer> roomTypeMap = new HashMap<>();

    public static final Map<String, Integer> bedroomNumMap = new HashMap<>();

    public static final Map<String, Integer> bathroomNumMap = new HashMap<>();

    public static final Map<String, Integer> balconyMap = new HashMap<>();

    public static final Map<String, Integer> durationMap = new HashMap<>();

    public static final Map<Integer, Integer> dateRangeMap = new HashMap<>();

        public static final Map<String, Integer> sexMap = new HashMap<>();

    public static final Map<String, Integer> jobMap = new HashMap<>();

    public static final Map<String, Integer> petMap = new HashMap<>();

    public static final Integer THRESHOLD = 8;

    public static final String UNLIMITED = "Unlimited";
    public static final String HOUSE = "House";
    public static final String TOWNHOUSE = "Townhouse";
    public static final String STUDIO = "Studio";
    public static final String APARTMENT = "Apartment";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String UNDER_1_MONTH = "Under 1 Month";
    public static final String IN_1_3_MONTHS = "1-3 Months";
    public static final String IN_3_6_MONTHS = "3-6 Months";
    public static final String IN6_12_MONTHS = "6-12 Months";
    public static final String OVER_1_YEAR = "Over 1 year";
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public static final String STUDENT = "Student";
    public static final String TEACHER = "Teacher";
    public static final String DOCTOR = "Doctor";
    public static final String ENGINEER = "Engineer";
    public static final String ACCOUNTANT = "Accountant";
    public static final String BANK_CLERK = "Bank Clerk";
    public static final String ARCHITECT = "Architect";
    public static final String POLICE_OFFICER = "Police Officer";
    public static final String CHEF = "Chef";
    public static final String LAWYER = "Lawyer";
    public static final String OTHERS = "Others";
    public static final String NO_PET = "No Pet";
    public static final String DOG = "Dog";
    public static final String CAT = "Cat";




    static {
        roomTypeMap.put(HOUSE, 1);
        roomTypeMap.put(TOWNHOUSE, 1);
        roomTypeMap.put(STUDIO, 1);
        roomTypeMap.put(APARTMENT, 1);
        roomTypeMap.put(UNLIMITED, 5);

        bedroomNumMap.put(ONE, 1);
        bedroomNumMap.put(TWO, 1);
        bedroomNumMap.put(THREE, 1);
        bedroomNumMap.put(FOUR, 1);
        bedroomNumMap.put(FIVE, 1);
        bedroomNumMap.put(SIX, 1);
        bedroomNumMap.put(UNLIMITED, 5);

        bathroomNumMap.put(ONE, 1);
        bathroomNumMap.put(TWO, 1);
        bathroomNumMap.put(THREE, 1);
        bathroomNumMap.put(FOUR, 1);
        bathroomNumMap.put(FIVE, 1);
        bathroomNumMap.put(SIX, 1);
        bathroomNumMap.put(UNLIMITED, 5);

        balconyMap.put(YES, 1);
        balconyMap.put(NO, 1);
        balconyMap.put(UNLIMITED, 5);

//        durationMap.put("Not Sure", 3);
        durationMap.put(UNDER_1_MONTH, 2);
        durationMap.put(IN_1_3_MONTHS, 1);
        durationMap.put(IN_3_6_MONTHS, 2);
        durationMap.put(IN6_12_MONTHS, 2);
        durationMap.put(OVER_1_YEAR, 3);

        dateRangeMap.put(0, 5);
        dateRangeMap.put(1, 4);
        dateRangeMap.put(2, 3);
        dateRangeMap.put(3, 3);
        dateRangeMap.put(4, 2);
        dateRangeMap.put(5, 1);

        sexMap.put(MALE, 1);
        sexMap.put(FEMALE, 1);
        sexMap.put(UNLIMITED, 5);

        jobMap.put(STUDENT, 1);
        jobMap.put(TEACHER, 1);
        jobMap.put(DOCTOR, 1);
        jobMap.put(ENGINEER, 1);
        jobMap.put(ACCOUNTANT, 1);
        jobMap.put(BANK_CLERK, 1);
        jobMap.put(ARCHITECT, 1);
        jobMap.put(POLICE_OFFICER, 1);
        jobMap.put(CHEF, 1);
        jobMap.put(LAWYER, 1);
        jobMap.put(OTHERS, 1);
        jobMap.put(UNLIMITED, 5);

        petMap.put(NO_PET, 1);
        petMap.put(DOG, 1);
        petMap.put(CAT, 1);
        petMap.put(OTHERS, 1);
        petMap.put(UNLIMITED, 5);
    }
}
