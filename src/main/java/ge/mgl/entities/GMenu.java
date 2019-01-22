package ge.mgl.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Entity
@Table(name = "g_menu")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GMenu extends GSuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "order_n")
    private Integer orderN = 0;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "href")
    private String href;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "video_url")
    private String videoURL;

    @Lob
    @Column(name = "files")
    private String files;

    @Transient
    private List<String> array = new ArrayList<>();

    @Lob
    @Column(name = "descr")
    private String descr;

    @Transient
    public List<GMenu> nodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderN() {
        return orderN;
    }

    public void setOrderN(Integer orderN) {
        this.orderN = orderN;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<String> getArray() {
        return array;
    }

    public void setArray(List<String> array) {
        this.array = array;
    }

    public List<GMenu> getNodes() {
        return nodes;
    }

    public void setNodes(List<GMenu> nodes) {
        this.nodes = nodes;
    }
}
