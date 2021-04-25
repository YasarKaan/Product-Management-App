package app;

import data.Data;
import jsonoperations.JsonHelper;
import jsonoperations.JsonRead;
import jsonoperations.JsonWrite;
import menus.EmployeeMenu;
import menus.SignInScreen;
import products.*;
import status.StatusHelper;
import userhelpers.ManagerHelper;
import users.User;
import users.UserPriorities;

import java.util.ArrayList;

public class ProductManagementApp {
    public static void main(String[] args) {
        //Nulls are because of the empty json files(product-assembly-part)
        //Username= Admin password=123
        //We used lombok for getter&setter
        JsonHelper.read();
        SignInScreen.signInPage();
        JsonHelper.write();

    }
}