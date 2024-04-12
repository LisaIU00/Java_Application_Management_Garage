/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.price;

/**
 *
 * @author l.uccini
 */
public class Price {

    private double[] first_hour = new double[6];
    private double[] hour = new double[6];
    private double[] day = new double[6];
    private double[] month = new double[6];
    private double[] year = new double[6];

    private static Price instance = null;

    public static Price getPrice() {
        if (instance == null) {
            instance = new Price();
        }
        return instance;
    }

    public void refactor() {
        instance = null;
    }

    private Price() {
        //luxury
        first_hour[0] = 001;
        hour[0] = 111;
        day[0] = 222;
        month[0] = 333;
        year[0] = 444;

        //maxicar
        first_hour[1] = 3;
        hour[1] = 2;
        day[1] = 39.9;
        month[1] = 780;
        year[1] = 8100;

        //maxi
        first_hour[2] = 4;
        hour[2] = 3;
        day[2] = 60;
        month[2] = 1640;
        year[2] = 9983;

        //midi
        first_hour[3] = 3;
        hour[3] = 2;
        day[3] = 36;
        month[3] = 750;
        year[3] = 7899;

        //mini
        first_hour[4] = 2;
        hour[4] = 2;
        day[4] = 34;
        month[4] = 750;
        year[4] = 7899;

        //moto
        first_hour[5] = 2;
        hour[5] = 2;
        day[5] = 34;
        month[5] = 750;
        year[5] = 7899;
    }

    public boolean checkPrice() {
        for (int i = 0; i < 6; i++) {
            if (first_hour[i] > day[i] || hour[i] > day[i] || day[i] > month[i] || month[i] > year[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean modifyPrice(String category, String plane, double price) {

        double old;
        switch (plane) {
            case "first_hour":
                old = getPrice_first_hour(category);
                setPrice_first_hour(price, category);
                if (!checkPrice()) {
                    setPrice_first_hour(old, category);
                    return false;
                }
                return true;
            case "hour":
                old = getPrice_hour(category);
                setPrice_hour(price, category);
                if (!checkPrice()) {
                    setPrice_hour(old, category);
                    return false;
                }
                return true;
            case "day":
                old = getPrice_day(category);
                setPrice_day(price, category);
                if (!checkPrice()) {
                    setPrice_day(old, category);
                    return false;
                }
                return true;
            case "month":
                old = getPrice_month(category);
                setPrice_month(price, category);
                if (!checkPrice()) {
                    setPrice_month(old, category);
                    return false;
                }
                return true;
            case "year":
                old = getPrice_year(category);
                setPrice_year(price, category);
                if (!checkPrice()) {
                    setPrice_year(old, category);
                    return false;
                }
                return true;
        }
        return false;
    }

    private int calculateIndex(String category) {
        int i = -1;
        switch (category) {
            case "LC":
                i = 0;
                break;
            case "MXC":
                i = 1;
                break;
            case "MX":
                i = 2;
                break;
            case "MDC":
                i = 3;
                break;
            case "MNC":
                i = 4;
                break;
            case "MT":
                i = 5;
        }
        return i;
    }

    public double getPrice_first_hour(String category) {
        return first_hour[calculateIndex(category)];
    }

    public double getPrice_hour(String category) {
        return hour[calculateIndex(category)];
    }

    public double getPrice_day(String category) {
        return day[calculateIndex(category)];
    }

    public double getPrice_month(String category) {
        return month[calculateIndex(category)];
    }

    public double getPrice_year(String category) {
        return year[calculateIndex(category)];
    }

    public void setPrice_first_hour(double p, String category) {
        first_hour[calculateIndex(category)] = p;
    }

    public void setPrice_hour(double p, String category) {
        hour[calculateIndex(category)] = p;
    }

    public void setPrice_day(double p, String category) {
        day[calculateIndex(category)] = p;
    }

    public void setPrice_month(double p, String category) {
        month[calculateIndex(category)] = p;
    }

    public void setPrice_year(double p, String category) {
        year[calculateIndex(category)] = p;
    }

    public double getSubscriptionAmount(String category, boolean annual) {
        if (annual) {
            return getPrice_year(category);
        }
        return getPrice_month(category);
    }

}
