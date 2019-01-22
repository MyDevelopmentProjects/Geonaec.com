package ge.mgl.pojo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Mikheil on 11/27/2017.
 */
public class UserGoodCommentPOJO {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
