package products;

import lombok.Data;
import status.Complete;
import status.IStatus;
import status.InProgress;
import status.NotStarted;


@Data
public abstract class Warehouse {
    private String title;
    private int id;
    private IStatus status = new NotStarted();


    public void changeStatus(){
        setStatus(status.changeStatus());

    }


}
