package menus;

import data.Data;
import io.Input;
import io.SignInHelper;
import userhelpers.AdminHelper;
import userhelpers.Helper;
import users.User;
import users.UserPriorities;

import java.util.Objects;

public class SignInScreen {


    public static User getUser(){
        return SignInHelper.signIn(Data.staffList);
    }

    public static void signInPage() {
        while(true) {
            User user = getUser();
            if (user == null) {
                System.out.println("User not found");
                continue;
            }
            else if (user.getUserType().equals(UserPriorities.ADMIN)) {
                AdminMenu.adminScreen(user);
            } else if (user.getUserType().equals(UserPriorities.MANAGER)) {
                ManagerMenu.managerScreen(user);
            } else if (user.getUserType().equals(UserPriorities.EMPLOYEE)) {
                EmployeeMenu.employeeScreen(user);
            }
            break;
        }
    }

}