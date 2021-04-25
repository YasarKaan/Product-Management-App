package products;

import lombok.*;
import status.Complete;
import status.IStatus;
import status.InProgress;
import status.NotStarted;
import users.User;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product extends Warehouse{
    private List<ICombined> combinedList = new ArrayList<>();
    private User responsibleManager;
    private User createdAdmin;

    public String toString(){
        return "Product{" +
                "Title='" + getTitle() + '\'' +
                ", Id=" + getId() +
                ", Status=" + getStatus().toString() +
                ", Responsible Manager=" + getResponsibleManager() +
                ", Created Admin=" + getCreatedAdmin() +
                ", Combined=" + getCombinedList() +
        '}';
    }

}
