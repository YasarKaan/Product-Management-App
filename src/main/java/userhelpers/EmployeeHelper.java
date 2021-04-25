package userhelpers;

import products.*;

public class EmployeeHelper {

    public static void employeeChangeStatus(Warehouse warehouse){
        if(warehouse instanceof Part){
            Part part = (Part) warehouse;
            part.changeStatus();
            employeeChangeStatus(part.getParent());
        }
        else if(warehouse instanceof Assembly){
            Assembly assembly = (Assembly) warehouse;
            if(assembly.getStatus().getClass().getSimpleName().equals("NotStarted")){
                assembly.changeStatus();
            }
            else if(assembly.getStatus().getClass().getSimpleName().equals("InProgress")){
                int completedAmount=0;
                int size=assembly.getProducedList().size();
                for(ICombined combined : assembly.getProducedList()){
                    if(combined.gettingStatus().getClass().getSimpleName().equals("Complete")){
                        completedAmount++;
                    }
                }
                if(completedAmount==size){
                    assembly.changeStatus();
                    employeeChangeStatus(assembly.getParent());
                }
            }
        } else if(warehouse instanceof Product){
            Product product = (Product) warehouse;
            if (product.getStatus().getClass().getSimpleName().equals("NotStarted")){
                product.changeStatus();
            }
            else if(product.getStatus().getClass().getSimpleName().equals("InProgress")){
                int completedAmount=0;
                int size = product.getCombinedList().size();
                for(ICombined combined : product.getCombinedList()){
                    if(combined.gettingStatus().getClass().getSimpleName().equals("Complete")){
                        completedAmount++;
                    }
                }
                if(completedAmount==size){
                    product.changeStatus();
                }
            }
        }

    }
}