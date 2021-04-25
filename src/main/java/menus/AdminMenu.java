package menus;

import data.Data;
import io.Input;
import userhelpers.AdminHelper;
import userhelpers.Helper;
import users.User;

import java.util.Objects;

public class AdminMenu {
    public static void adminScreen(User user){
        while (true) {
            System.out.println(" Hello " + user.getUserName());
            System.out.println(" What do you want? " +
                    "\n For adding Product and Manager Press 1 " +
                    "\n For see all Managers Press 2 " +
                    "\n For see all Employees Press 3 " +
                    "\n For see product and related Assemblies and Parts Press 4 " +
                    "\n 0 for Exit");
            int choice = Input.getMenuInput(5);
            if(choice==1){
                AdminHelper.createProduct(user);
            }
            else if(choice==2){
                AdminHelper.displayUserType("MANAGER",Data.staffList);
            }
            else if(choice==3){
                AdminHelper.displayUserType("EMPLOYEE",Data.staffList);
            }
            else if(choice==4){
                selectPrint(user);
            }
            else if(choice==0){
                break;
            }
        }
    }
    private static void selectPrint(User user) {
        while (true) {
            System.out.println(" What do you want? " +
                    "\n For see Product List Press 1 " +
                    "\n For see Assembly List Press 2 " +
                    "\n For see Part List Press 3 " +
                    "\n For see product and related Assemblies and Parts Press 4 " +
                    "\n 0 for Exit");
            int choice = Input.getMenuInput(5);
            if(choice==1){
                AdminHelper.showProductsList();
            }
            else if(choice==2){
                AdminHelper.showAssemblyList();
            }
            else if(choice==3){
                AdminHelper.showPartsList();
            }
            else if(choice==4){
                Helper.printProductTree(user);
            }
            else if(choice==0){
                break;
            }
        }
    }

}
