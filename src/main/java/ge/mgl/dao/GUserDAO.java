package ge.mgl.dao;

import ge.mgl.entities.GUser;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GUserDAO extends PaginationAndFullSearchQuery<GUser> {

    @Autowired
    private MessageSource messageSource;

    public GUserDAO() {
        super(GUser.class);
    }

    public GUser findByUserName(String username) {
        List<GUser> users;
        users = getEntityManager().createQuery("FROM GUser where username=:username and active = true")
                .setParameter("username", username)
                .getResultList();
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public GUser findUserByUsernamePassword(String username, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String hashedPassword = passwordEncoder.encode(password);

        List<GUser> user = getEntityManager().createQuery("FROM GUser where username=:username and password=:password")
                .setParameter("username", username)
                .setParameter("password", hashedPassword)
                .getResultList();
        if(user != null && user.size() > 0){
            return user.get(0);
        }
        return null;
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        List<String> fieldList = new ArrayList<>();
        if (resultClass == GUser.class) {
            fieldList.add("userRoomCode");
            fieldList.add("identification");
            fieldList.add("username");
            fieldList.add("phone");
        }
        return fieldList;
    }
}
