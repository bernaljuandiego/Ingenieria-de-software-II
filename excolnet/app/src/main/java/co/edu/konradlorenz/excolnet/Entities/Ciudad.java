package co.edu.konradlorenz.excolnet.Entities;

import java.util.ArrayList;
import java.util.List;

public class Ciudad {

    private String name;
    private String currency;
    private int monthLastUpdate;
    private int contributors;
    private int yearLastUpdate;
    private List<Precios> prices;
    private int city_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMonthLastUpdate() {
        return monthLastUpdate;
    }

    public void setMonthLastUpdate(int monthLastUpdate) {
        this.monthLastUpdate = monthLastUpdate;
    }

    public int getContributors() {
        return contributors;
    }

    public void setContributors(int contributors) {
        this.contributors = contributors;
    }

    public int getYearLastUpdate() {
        return yearLastUpdate;
    }

    public void setYearLastUpdate(int yearLastUpdate) {
        this.yearLastUpdate = yearLastUpdate;
    }



    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public List<Precios> getPrices() {
        return prices;
    }

    public void setPrices(List<Precios> prices) {
        this.prices = prices;
    }
}
