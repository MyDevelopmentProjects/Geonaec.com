package ge.mgl.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Mikheil on 11/15/2017.
 */
public class UserAuthPOJO {

    @NotEmpty
    @Email
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String password;

    private String gcheck;

    private boolean remember;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGcheck() {
        return gcheck;
    }

    public void setGcheck(String gcheck) {
        this.gcheck = gcheck;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
