package data;

import lombok.ToString;
import products.Assembly;
import products.Part;
import products.Product;
import products.Warehouse;
import users.User;

import java.util.ArrayList;
@ToString
public class Data {
    public static ArrayList<User> staffList = new ArrayList<>();
    public static ArrayList<Warehouse> warehouseList = new ArrayList<>();
    public static ArrayList<Product> productList=new ArrayList<>();
    public static ArrayList<Part> partsList = new ArrayList<>();
    public static ArrayList<Assembly> assembliesList = new ArrayList<>();

}
