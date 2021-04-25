package status;

import products.Warehouse;

public class Complete implements IStatus{
    @Override
    public IStatus changeStatus() {
        System.out.println(" Already Completed!!!!!");
        return new Complete();
    }
}