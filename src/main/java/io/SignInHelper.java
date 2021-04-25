package io;
import users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class SignInHelper {
    private static User signInHelp(ArrayList<User> staffList, String userName, String password){
        int check=0;
        for (User user : staffList) {
            if (user.getUserName().equals(userName)) {
                check++;
                if (user.getPassword().equals(password)) {
                    return user;

                } else {
                    System.out.println("Wrong password!");
                    break;
                }
            }
        }
        if(check==0){
            System.out.println("User can not found!");
            return null;
        }
        return null;
    }

    public static User signIn(ArrayList<User> staffList) {
        int tryTimes=3;
        while (tryTimes!=0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter User Name: ");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            try{
                User user = signInHelp(staffList, email, password);
                if(user != null) {
                    System.out.println("Successful");
                    return user;
                }
                else{
                    return null;
                }
            }catch (NullPointerException e){
                tryTimes=tryTimes-1;
                if(tryTimes==0) {
                    System.out.println("Failed Try Again");
                    return null;
                }
                else if(tryTimes==1){
                    System.out.println("Last chance be careful");
                }
                else{
                    System.out.println("Left " + tryTimes + " try chance");
                }
            }
        }
        return null;
    }

}
