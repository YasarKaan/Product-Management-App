package menus;

import data.Data;
import io.Input;
import products.Product;
import userhelpers.AdminHelper;
import userhelpers.Helper;
import userhelpers.ManagerHelper;
import users.User;

import java.util.Objects;

import static userhelpers.Helper.findProduct;

public class ManagerMenu {
    public static void managerScreen(User user){
        while (true) {
            System.out.println(" Hello " + user.getUserName());
            System.out.println(" What do you want? " +
                    "\n For Create Part and Employee Press 1 " +
                    "\n For Create Assembly Press 2 " +
                    "\n For see all Employees Press 3 " +
                    "\n For see product and related Assemblies and Parts Press 4 " +
                    "\n 0 for Exit");
            int choice = Input.getMenuInput(5);
            if(choice==1){
                Product product =findProduct(user);
                assert product != null;
                product.getCombinedList().add(ManagerHelper.createPart(user,product));
            }
            else if(choice==2){
                Product product =findProduct(user);
                assert product != null;
                product.getCombinedList().addAll(ManagerHelper.createAssembly(user,product)) ;
            }
            else if(choice==3){
                AdminHelper.displayUserType("EMPLOYEE", Data.staffList);
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
