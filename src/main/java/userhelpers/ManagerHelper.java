package userhelpers;

import data.Data;
import exceptions.UnauthorizedUserOperationException;
import io.Input;
import products.*;
import users.User;
import users.UserPriorities;

import java.util.ArrayList;

public class ManagerHelper {

    protected static User createEmployee(){
        User employee = new User();
        employee.setUserType(UserPriorities.EMPLOYEE);
        return Helper.getUserParameters(employee);
    }

    public static Part createPart(User manager,Warehouse parent) {
        try {
            Part part = new Part();
            User employee = createEmployee();
            Data.staffList.add(employee);
            System.out.println("Enter Part Title ");
            String partName= Input.getStringInput();
            part.setTitle(partName);
            part.setId(Data.warehouseList.size()+1);
            if(employee.getUserType().equals(UserPriorities.EMPLOYEE) &&
                    manager.getUserType().equals(UserPriorities.MANAGER)){
                part.setResponsibleEmployee(employee);
                part.setCreatedManager(manager);
                part.setParent(parent);
                Data.warehouseList.add(part);
                Data.partsList.add(part);
                return part;
            }
            else throw new UnauthorizedUserOperationException();
        }catch (UnauthorizedUserOperationException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static ArrayList<ICombined> createAssembly (User manager, Warehouse parent){
        ArrayList<ICombined> assemblies = new ArrayList<>();
        Assembly assembly = new Assembly();
        System.out.println("Assembly Title");
        assembly.setTitle(Input.getStringInput());
        assembly.setId(Data.warehouseList.size()+1);
        assembly.setCreatedManager(manager);
        assembly.setParent(parent);
        Data.warehouseList.add(assembly);
        Data.assembliesList.add(assembly);
        System.out.println("Do you have any assemblies "+assembly.getTitle() +" in this assembly? 0-NO 1-YES");
        int assemblyChoice = Input.getMenuInput(2);
        if(assemblyChoice==1){
            while(true){
                assembly.getProducedList().addAll(createAssembly(manager,assembly));
                System.out.println("Do you have more assemblies "+assembly.getTitle() +" in this assembly? 0-NO 1-YES");
                int moreAssemblyChoice = Input.getMenuInput(2);
                if(moreAssemblyChoice==0){
                    break;
                }
            }
            // If assembly has child assembly, we did a recursive function so it can create assembly inside Parent assembly
        }
        addPartsToAssembly(manager,assembly);
        assemblies.add(assembly);

        return assemblies;

    }

    private static void addPartsToAssembly(User manager, Assembly assembly){
        while(true) {

            if(assembly.getProducedList().size()<2){
                System.out.println("You need to add at least "+ (2-assembly.getProducedList().size()) +" part to the Assembly to the "+ assembly.getTitle());
                Part part = createPart(manager,assembly);
                assembly.getProducedList().add(part);
                System.out.println("You added "+ part.getTitleName() +" part to the "+ assembly.getTitle());
            }
            else{
                System.out.println("Do you want to add part ? 0-NO 1-YES");
                int partChoice = Input.getMenuInput(2);
                if(partChoice==0){
                    break;
                }
                else if(partChoice==1){
                    Part part = createPart(manager,assembly);
                    assembly.getProducedList().add(part);
                    System.out.println("You added "+ part.getTitleName() +" part to the "+ assembly.getTitle());
                }
            }
        }
    }

}