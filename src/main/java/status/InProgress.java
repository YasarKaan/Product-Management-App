package status;

import products.Warehouse;

public class InProgress implements IStatus{
    @Override
    public IStatus changeStatus() {
        System.out.println(" Status changed to Complete");
        return new Complete();
    }


}