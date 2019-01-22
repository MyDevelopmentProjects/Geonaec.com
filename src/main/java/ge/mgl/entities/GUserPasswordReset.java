package ge.mgl.entities;

import javax.persistence.*;

/**
 * Created by user on 11/26/17.
 */
@Entity
@Table(name = "g_user_password_reset")
public class GUserPasswordReset extends GSuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private GUser user;

    @Column(name = "hash_code")
    private String hashCode;

    @Column(name = "is_finished", columnDefinition = "bit(1) DEFAULT 0")
    private boolean finished = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GUser getUser() {
        return user;
    }

    public void setUser(GUser user) {
        this.user = user;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
