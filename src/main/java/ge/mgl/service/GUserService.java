package ge.mgl.service;

import ge.mgl.dao.GUserDAO;
import ge.mgl.entities.GUser;
import ge.mgl.pojo.UserRegistrationPOJO;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GUserService {

    @Autowired
    private GUserDAO userDAO;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return userDAO.getPaginatedList(searchExpression,
                sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public GUser loadUserByUsername(String username) {
        return userDAO.findByUserName(username);
    }


    @Transactional(readOnly = true)
    public GUser findUserByUsernamePassword(String username, String password) {
        return userDAO.findUserByUsernamePassword(username,password);
    }


    @Transactional
    public GUser save(GUser user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        if (user.getId() != null) {
            if(user.getPassword().length() != 60){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userDAO.update(user);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.create(user);
    }

    @Transactional(readOnly = true)
    public GUser findById(Long id){
        return userDAO.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return userDAO.delete(id);
    }

    @Transactional
    public Long getCount(){
        return userDAO.count();
    }
}
