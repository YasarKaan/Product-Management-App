package userhelpers;

import data.Data;
import io.Input;
import products.Assembly;
import products.ICombined;
import products.Part;
import products.Product;
import users.User;
import users.UserPriorities;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class AdminHelper {

    public static User createManager(){
        User manager = new User();
        manager.setUserType(UserPriorities.MANAGER);
        Data.staffList.add(manager);
        return Helper.getUserParameters(manager);
    }

    public static void createProduct(User admin){
        Product product = new Product();
        User manager = createManager();
        System.out.println("Enter Product Name");
        String productName = Input.getStringInput();
        product.setTitle(productName);
        product.setId(Data.warehouseList.size()+1);
        product.setCreatedAdmin(admin);
        product.setResponsibleManager(manager);
        Data.warehouseList.add(product);
        Data.productList.add(product);

    }

    public static void displayUserType(String userType, ArrayList<User> arrayList){
        if(userType.equals("MANAGER")){
            ArrayList<User> managerList = new ArrayList<>();

            for(User user:arrayList){
                if(user.getUserType().equals(UserPriorities.MANAGER)){
                    managerList.add(user);
                }
            }
            int i=1;
            for(User user: managerList){
                System.out.println(i +"-"+user.getUserName() + " " + user.getUserId());
                i++;
            }

        }
        else if(userType.equals("EMPLOYEE")){
            ArrayList<User> employeeList = new ArrayList<>();

            for(User user:arrayList){
                if(user.getUserType().equals(UserPriorities.EMPLOYEE)){
                    employeeList.add(user);
                }
            }
            int i=1;
            for(User user: employeeList){
                System.out.println(i +"-"+user.getUserName()+ " " +user.getUserId());
                i++;
            }
        }
        else{
            throw new InputMismatchException();
        }
    }

    public static void showProductsList(){
        int i=1;
        for (Product product:Data.productList){
            System.out.println(i +"-"+product.getId()+" "+product.getTitle());
            i++;
        }
    }
    public static void showAssemblyList(){
        int i=1;
        for (Assembly assembly:Data.assembliesList){
            System.out.println(i +"-"+assembly.getId()+" "+assembly.getTitle());
            i++;
        }
    }
    public static void showPartsList(){
        int i=1;
        for (Part part:Data.partsList){
            System.out.println(i +"-"+part.getId()+" "+part.getTitle());
            i++;
        }
    }


}
