package ge.mgl.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by user on 11/12/17.
 */
public class UserRegistrationPOJO {

    @NotEmpty
    @Email
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String repeatPassword;

    @NotEmpty
    private  String identification;

    private String firstNameGE;
    private String lastNameGE;

    @NotEmpty
    private String firstNameEN;

    @NotEmpty
    private String lastNameEN;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String address1;

    @NotEmpty
    private String address2;

    private String companyName;

    private boolean foreigner = false;

    private boolean juridical = false;

    private String captcha;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getFirstNameGE() {
        return firstNameGE;
    }

    public void setFirstNameGE(String firstNameGE) {
        this.firstNameGE = firstNameGE;
    }

    public String getLastNameGE() {
        return lastNameGE;
    }

    public void setLastNameGE(String lastNameGE) {
        this.lastNameGE = lastNameGE;
    }

    public String getFirstNameEN() {
        return firstNameEN;
    }

    public void setFirstNameEN(String firstNameEN) {
        this.firstNameEN = firstNameEN;
    }

    public String getLastNameEN() {
        return lastNameEN;
    }

    public void setLastNameEN(String lastNameEN) {
        this.lastNameEN = lastNameEN;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isForeigner() {
        return foreigner;
    }

    public void setForeigner(boolean foreigner) {
        this.foreigner = foreigner;
    }

    public boolean isJuridical() {
        return juridical;
    }

    public void setJuridical(boolean juridical) {
        this.juridical = juridical;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
