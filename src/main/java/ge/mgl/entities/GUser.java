package ge.mgl.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "g_user")
public class GUser extends GSuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private GRole role;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "user_room_code", unique = true, nullable = false)
    private String userRoomCode;

    @Column(name = "identification", unique = true)
    private String identification;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Expose(serialize = false)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name_ge")
    private String firstNameGE;

    @Column(name = "last_name_ge")
    private String lastNameGE;

    @Column(name = "first_name_en")
    private String firstNameEN;

    @Column(name = "last_name_en")
    private String lastNameEN;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "is_foreigner", columnDefinition = "bit(1) DEFAULT 0")
    private boolean foreigner = false;

    @Column(name = "is_juridical", columnDefinition = "bit(1) DEFAULT 0")
    private boolean juridical = false;

    @Column(name = "is_active", columnDefinition = "bit(1) DEFAULT 1")
    private boolean active = true;

    public GUser() {
    }

    public GUser(GUser user) {
        this.id = user.id;
        this.role = user.role;
        this.imgUrl = user.imgUrl;
        this.identification = user.identification;
        this.userRoomCode = user.userRoomCode;
        this.username = user.username;
        this.password = user.password;
        this.firstNameGE = user.firstNameGE;
        this.lastNameGE = user.lastNameGE;
        this.firstNameEN = user.firstNameEN;
        this.lastNameEN = user.lastNameEN;
        this.phone = user.phone;
        this.address1 = user.address1;
        this.address2 = user.address2;
        this.companyName = user.companyName;
        this.foreigner = user.foreigner;
        this.juridical = user.juridical;
        this.active = user.active;
        this.dateCreated = user.dateCreated;
        this.dateUpdated = user.dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GRole getRole() {
        return role;
    }

    public void setRole(GRole role) {
        this.role = role;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUserRoomCode() {
        return userRoomCode;
    }

    public void setUserRoomCode(String userRoomCode) {
        this.userRoomCode = userRoomCode;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
