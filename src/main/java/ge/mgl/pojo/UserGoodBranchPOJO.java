package ge.mgl.pojo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by user on 12/3/17.
 */
public class UserGoodBranchPOJO {

    @NotEmpty
    private Long id;

    @NotEmpty
    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
