package status;

import products.Warehouse;

public class NotStarted implements IStatus{
    @Override
    public IStatus changeStatus() {
        System.out.println(" Status changed to In Progress");
        return new InProgress();
    }
}