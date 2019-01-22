package ge.mgl.entities;

import javax.persistence.*;

@Entity
@Table(name = "about")
public class GAbout extends GSuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "tab_title_left")
    private String tabTitleLeft;

    @Column(name = "tab_title_right")
    private String tabTitleRight;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "title1")
    private String title1;

    @Column(name = "title2")
    private String title2;

    @Lob
    @Column(name = "description1")
    private String description1;

    @Lob
    @Column(name = "description2")
    private String description2;

    @Column(name ="image1")
    private String image1;

    @Column(name ="image2")
    private String image2;

    @Column(name ="image3")
    private String image3;

    @Column(name ="image4")
    private String image4;

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

    public String getTabTitleLeft() {
        return tabTitleLeft;
    }

    public void setTabTitleLeft(String tabTitleLeft) {
        this.tabTitleLeft = tabTitleLeft;
    }

    public String getTabTitleRight() {
        return tabTitleRight;
    }

    public void setTabTitleRight(String tabTitleRight) {
        this.tabTitleRight = tabTitleRight;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
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
}
