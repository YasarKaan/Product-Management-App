package menus;


import io.Input;
import products.Part;
import products.Status;
import status.StatusHelper;
import userhelpers.EmployeeHelper;
import users.User;

import static userhelpers.Helper.findPart;

public class EmployeeMenu {
    public static void employeeScreen(User user) {
        while (true) {
            Part part = findPart(user);
            assert part != null;
            String partTitle = part.getTitle();
            System.out.println(" Hello " + user.getUserName());
            System.out.println(" What do you want? " +
                    "\n For Change Status to the " + partTitle + " Press 1 "+
                    "\n 0 for Exit");
            int choice = Input.getMenuInput(2);
            if (choice == 1) {
                    System.out.println(" Your part's status " + part.gettingStatus().getClass().getSimpleName());
                    EmployeeHelper.employeeChangeStatus(part);
                    System.out.println(" Your part's status " + part.gettingStatus().getClass().getSimpleName() + " now");
            }
            else if(choice==0) {
                break;
            }

        }
    }
}
