package status;

import products.*;

public class StatusHelper {
    public static void productStatusChanger(Product product){
        int statusChecker=0;
        int allMaterials=product.getCombinedList().size();
        for(ICombined combined:product.getCombinedList()){
            if(Complete.class.equals(combined.gettingStatus()) || InProgress.class.equals(combined.gettingStatus())){
                statusChecker++;
            }
        }
        if(statusChecker==allMaterials){
            product.changeStatus();
        }
    }
    public static void assemblyStatusChanger(Assembly assembly){
        int statusChecker=0;
        int allMaterials=assembly.getProducedList().size();
        for(ICombined combined: assembly.getProducedList()){
            if(Complete.class.equals(combined.gettingStatus()) || InProgress.class.equals(combined.gettingStatus())){
                statusChecker++;
            }
        }
        if(statusChecker==allMaterials){
            assembly.changeStatus();
        }
    }
}
