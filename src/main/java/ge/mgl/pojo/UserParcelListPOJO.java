package ge.mgl.pojo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/27/17.
 */
public class UserParcelListPOJO {

    @Valid
    private List<UserParcelItemPOJO> items = new ArrayList<>();

    public List<UserParcelItemPOJO> getItems() {
        return items;
    }

    public void setItems(List<UserParcelItemPOJO> items) {
        this.items = items;
    }
}
