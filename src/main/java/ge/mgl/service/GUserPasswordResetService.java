package ge.mgl.service;

import ge.mgl.dao.GUserPasswordResetDAO;
import ge.mgl.entities.GUser;
import ge.mgl.entities.GUserPasswordReset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by user on 11/26/17.
 */
@Service
public class GUserPasswordResetService {

    @Autowired
    private GUserPasswordResetDAO userPasswordResetDAO;

    @Transactional
    public GUserPasswordReset findByHashCode(String hashCode){
        return userPasswordResetDAO.findByHashCode(hashCode);
    }

    @Transactional
    public GUserPasswordReset create(GUserPasswordReset reset){
        return userPasswordResetDAO.create(reset);
    }

    @Transactional
    public GUserPasswordReset update(GUserPasswordReset userPasswordReset) {
        return userPasswordResetDAO.update(userPasswordReset);
    }
}
