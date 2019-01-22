package ge.mgl.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Entity
@Table(name = "g_sponsors")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GSponsors extends GSuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name ="image1")
    private String image1;

    @Column(name ="image2")
    private String image2;

    @Column(name ="image3")
    private String image3;

    @Column(name ="image4")
    private String image4;

    @Column(name ="image5")
    private String image5;

    @Column(name ="image6")
    private String image6;

    @Column(name ="href1")
    private String href1;

    @Column(name ="href2")
    private String href2;

    @Column(name ="href3")
    private String href3;

    @Column(name ="href4")
    private String href4;

    @Column(name ="href5")
    private String href5;

    @Column(name ="href6")
    private String href6;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public String getHref1() {
        return href1;
    }

    public void setHref1(String href1) {
        this.href1 = href1;
    }

    public String getHref2() {
        return href2;
    }

    public void setHref2(String href2) {
        this.href2 = href2;
    }

    public String getHref3() {
        return href3;
    }

    public void setHref3(String href3) {
        this.href3 = href3;
    }

    public String getHref4() {
        return href4;
    }

    public void setHref4(String href4) {
        this.href4 = href4;
    }

    public String getHref5() {
        return href5;
    }

    public void setHref5(String href5) {
        this.href5 = href5;
    }

    public String getHref6() {
        return href6;
    }

    public void setHref6(String href6) {
        this.href6 = href6;
    }
}
