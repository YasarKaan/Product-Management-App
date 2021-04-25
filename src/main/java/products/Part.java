package products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import status.IStatus;
import users.User;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Part extends Warehouse implements ICombined {
    private User createdManager;
    private User responsibleEmployee;
    private Warehouse parent;

    @Override
    public String getTitleName() {
        return getTitle();
    }

    @Override
    public int getIds() {
        return getId();
    }

    @Override
    public void changingStatus() {
        changeStatus();
    }

    public IStatus gettingStatus(){
        return getStatus();
    }

    public String toString(){
        return "Part Name"+ getTitleName() + "Id" + getIds() + "Created Manager" + getCreatedManager().getUserName() +
                "Responsible Employee "+getResponsibleEmployee().getUserName() +"Status"+getStatus();
    }
}