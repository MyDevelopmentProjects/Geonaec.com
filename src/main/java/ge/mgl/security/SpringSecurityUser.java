package ge.mgl.security;

import ge.mgl.entities.GUser;
import ge.mgl.entities.GRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Mikheil on 3/17/17.
 */
public class SpringSecurityUser extends GUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public SpringSecurityUser(GUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user);
        this.setAuthorities(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public GRole getRole() {
        return super.getRole();
    }

}
