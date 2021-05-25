package com.example.warrantyreminder;

public class DataModel {
    private static DataModel instance = null;

    public static DataModel getInstance() {
        if(instance == null) instance = new DataModel();
        return instance;
    }

    public static final int WARRANTY_YEARS = 0;
    public static final int WARRANTY_MONTHS = 1;
    public static final int WARRANTY_DAYS = 2;

    public static int dateYear;
    public static int dateMonth;
    public static int dateDayOfMonth;

    public static String product;
    public static String store;
    public static int warrantyLength;
    public static int warrantyLengthType;

    public static SecondFragment secondFragment;

    public static void dumpDB() {


    }

    public static void reset() {
        dateYear = 0;
        dateMonth = 0;
        dateDayOfMonth = 0;

        product = "";
        store = "";
        warrantyLengthType = 0;
        warrantyLength = 0;
        secondFragment = null;
    }


    private DataModel() {}
}
