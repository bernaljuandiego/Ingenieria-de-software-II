package co.edu.konradlorenz.excolnet.Entities;

public class Precios {
    private int data_points;
    private int item_id;
    private int lowest_price;
    private int average_price;
    private int highest_price;
    private String item_name;

    public int getData_points() {
        return data_points;
    }

    public void setData_points(int data_points) {
        this.data_points = data_points;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(int lowest_price) {
        this.lowest_price = lowest_price;
    }

    public int getAverage_price() {
        return average_price;
    }

    public void setAverage_price(int average_price) {
        this.average_price = average_price;
    }

    public int getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(int highest_price) {
        this.highest_price = highest_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
