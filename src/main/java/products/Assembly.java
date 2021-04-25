package products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import status.IStatus;
import users.User;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assembly extends Warehouse implements ICombined {
    private User createdManager;
    private Warehouse parent;
    private List<ICombined> producedList = new ArrayList<>();

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
        String partStr = "";
        String assStr = "";
        for (ICombined combined: producedList){
            if(combined instanceof Assembly){
                assStr=toString();
            }else if(combined instanceof Part){
                partStr = combined.toString();
            }
        }
        return "Assembly name"+getTitle() + "Assembly Id" + getId() + "Created Manager" + getCreatedManager().getUserName() +
                "Parent" + getParent().getTitle() + assStr+partStr;

    }
}
