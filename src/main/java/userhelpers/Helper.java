package userhelpers;

import data.Data;
import io.Input;
import products.*;
import users.User;

import java.util.ArrayList;
import java.util.Objects;

public class Helper {

    public User createUser(User user){
        return switch (user.getUserType()) {
            case ADMIN -> AdminHelper.createManager();
            case MANAGER -> ManagerHelper.createEmployee();
            default -> null;
        };
    }

    protected static User getUserParameters(User user) {
        System.out.println("Enter User Name");
        String userName = Input.getStringInput();
        System.out.println("Enter Password");
        String password = Input.getStringInput();

        user.setUserId(Data.staffList.size()+1);
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }

    public static Product findProduct(User user){
        for(Product product :Data.productList){
            if(product.getResponsibleManager().equals(user)||product.getCreatedAdmin().equals(user)){
                return product;
            }
        }
        return null;
    }

    public static Part findPart(User user){
        for(Part part :Data.partsList){
            if(part.getResponsibleEmployee().equals(user)){
                return part;
            }
        }
        return null;
    }
    public static Assembly findAssembly(String title){
        for(Assembly assembly :Data.assembliesList){
            if(assembly.getTitle().equals(title)){
                return assembly;
            }
        }
        return null;
    }

    public static void showAssembly(Assembly parentAssembly) {
        System.out.print("Selected Assembly: " + parentAssembly.getTitle() + " " +parentAssembly.getStatus().getClass().getSimpleName());
        for (ICombined combined : parentAssembly.getProducedList()) {
            if(combined.getClass().equals(parentAssembly.getClass())){
                System.out.print("\n");
                System.out.print(" Assemblies: ");
                System.out.print(combined.getTitleName()+", "+((Assembly) combined).getStatus().getClass().getSimpleName());
            }
            else{
                System.out.print("\n");
                System.out.print(" Parts: ");
                System.out.print(combined.getTitleName()+", "+ combined.gettingStatus().getClass().getSimpleName());
            }
        }
        System.out.print("\n");
        for (ICombined combined : parentAssembly.getProducedList()) {
            if (combined.getClass().equals(parentAssembly.getClass())) {
                Assembly assembly = (Assembly) combined;
                showAssembly(assembly);
            }
        }
    }
    public static void showProductTree(Product product){
        System.out.print("Selected product: " + product.getTitle()+ " " +product.getStatus().getClass().getSimpleName());
        for(ICombined combined: product.getCombinedList()){
            if(combined.getClass().equals(Assembly.class)){
                System.out.print("\n");
                System.out.print(" Assembly: ");
                System.out.print(combined.getTitleName()+", " + ((Assembly) combined).getStatus().getClass().getSimpleName());
            }
            else{
                System.out.print("\n");
                System.out.print("Part: ");
                System.out.print(combined.getTitleName()+", "+ combined.gettingStatus().getClass().getSimpleName());
            }
        }
        System.out.print("\n");
        for(ICombined combined: product.getCombinedList()){
            if (combined.getClass().equals(Assembly.class)) {
                showAssembly((Assembly) combined);
            }
        }
    }
    public static void printProductTree(User user){
        while (true) {
            System.out.println("\n For see Product Tree Press 1 " +
                    "\n 0 for Exit");
            int treeChoice = Input.getMenuInput(2);
            if (treeChoice == 1) {
                Helper.showProductTree(Helper.findProduct(user));
            } else if (treeChoice == 0) {
                break;
            }
        }
    }
}