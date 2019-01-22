package ge.mgl.entities;

/**
 * Created by MJaniko on 1/7/2017.
 */

import javax.persistence.*;

@Entity
@Table(name = "variable")
public class GVariable extends GSuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "name",  unique = true, nullable = false)
    private String name;

    @Column(name = "value", columnDefinition = "longtext COLLATE utf8mb4_unicode_ci")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
